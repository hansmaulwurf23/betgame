package de.betgame.job

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.quartz.InterruptableJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;


/**
 * Quartz Job implementation that invokes a specified method of an object, or static if class is specified.
 * this has the DisallowConcurrentExecution set to true
 */
public class InvokeMethodJob implements InterruptableJob {
    private static Logger log = LoggerFactory.getLogger(InvokeMethodJob.class);
    
    Class targetClass   //the class if this is a static
    Object targetObject //String   targetBeanName
    String targetMethod //String   staticMethod
    Object arguments
    
    def execution
    def id
    def monitor
    
    public InvokeMethodJob() {
    }
    
    void execute(JobExecutionContext jobCtx) {
        
        execution = jobCtx.mergedJobDataMap
        
        targetMethod = execution.targetMethod
        targetObject = execution.targetObject
        targetClass = execution.targetClass
        arguments = execution.arguments
        
        log.debug("BeanMethodJob called for $targetObject method:$targetMethod targetClass:${targetClass?.name} execution:${execution}")
        
        id = execution.id
        
        long start = System.currentTimeMillis();
        
        def mon = execution.monitor ?: [:]
        
        monitor = jobCtx.mergedJobDataMap.get('monitor') ?: mon
        monitor.id = id
        monitor.lastRun = new Date();
        monitor.status = 'running'
        
        monitor.processed = 0;
        monitor.totalCount = 100;
        
        monitor.fireTime = start;
        
        monitor.error = null;
        monitor.interrupted = false;
        
        log.debug "Adding monitor to: " + id
        jobCtx.mergedJobDataMap.put('monitor', monitor);
        jobCtx.scheduler.context.put(id, monitor);
        
        
        try {
            if (targetClass) {
                invokeStatic(jobCtx)
            } else if (targetObject) {
                invokeNormal(jobCtx)
            } else {
                throw new JobExecutionException("Either targetClass or targetObject need to be set for InvokeMethodJob.")
            }
        } catch (e) {
            monitor.error = e.getMessage();
            monitor.status = 'error'
            if (e instanceof JobExecutionException) {
                throw (JobExecutionException) e;
            }
            throw new JobExecutionException(e.getMessage(), e);
        }
        
        def end = System.currentTimeMillis();
        monitor.status = 'complete'
        monitor.duration = (end - start);
        monitor.endTime = end
        monitor.processed = 100
        
        jobCtx.mergedJobDataMap.put('monitor', monitor);
        log.debug "${id} Call took: " + ((end - start) / 1000)
    }
    
    void invokeNormal(JobExecutionContext jobCtx) {
        try {
            if (!targetMethod) throw new JobExecutionException("targetMethod must be specified")
            
            jobCtx.result = targetObject.invokeMethod(targetMethod, makeArgs())
        }
        catch (JobExecutionException e) {
            throw e
        }
        catch (e) {
            throw new JobExecutionException(e)
        }
    }
    
    void invokeStatic(JobExecutionContext jobCtx) {
        try {
            jobCtx.result = targetClass.metaClass.invokeStaticMethod(targetClass, targetMethod, makeArgs())
        } catch (JobExecutionException e) {
            throw e
        } catch (e) {
            throw new JobExecutionException(e)
        }
    }
    
    Object[] makeArgs() {
        def arguments = execution.arguments
        if (!arguments) return new Object[0]
        if (arguments.class.isArray()) return arguments
        if (arguments instanceof Collection) return arguments.toArray()
        //its a single argument then
        def o = new Object[1]
        o[0] = arguments
        return o
    }
    
    @Override
    public void interrupt() throws UnableToInterruptJobException {
        log.warn "${id} Interrupt requested: " + new Date()
        monitor.interrupted = true;
        log.warn "Interrupted monitor: " + monitor
    }
}