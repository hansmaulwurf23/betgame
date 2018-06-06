package de.betgame

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import org.quartz.JobKey
import org.quartz.Scheduler
import org.quartz.impl.matchers.GroupMatcher
import org.quartz.impl.matchers.StringMatcher

@Secured(['ROLE_MAILADMIN', 'ROLE_IDMADMIN'])
class QuartzController {
    Scheduler quartzScheduler
    def scriptService

    def index() {
        redirect(action: "list")
    }

    def list() {
        def jobScripts = scriptService.readFolder('jobs')
        def jobsData = [:].withDefault { [:] }
        
        jobScripts.each { js ->
            jobsData[js.name].status = doGetJobStatus(js.name)
        }
    
        quartzScheduler.jobGroupNames?.each { jobGroup ->
            quartzScheduler.getJobKeys(new GroupMatcher<JobKey>(jobGroup, StringMatcher.StringOperatorName.EQUALS))?.each { jobKey ->
                def regTriggers = quartzScheduler.getTriggersOfJob(jobKey)
                regTriggers.each { trigger ->
                    log.warn "${trigger.properties}"
                    jobsData[jobKey.name].trigger = trigger
                }
    
            }
        }
        
        log.warn "${jobsData}"
        
        [jobs: jobScripts*.name, now: new Date(), scheduler: quartzScheduler, jobsData: jobsData]
    }

    def stop(String jobName) {
        scriptService.runScript(jobName, [action:'stop'])
        redirect(action: "list")
    }

    def start(String jobName) {
        scriptService.runScript(jobName, [action:'start'])
        redirect(action: "list")
    }
    
    def status(String jobName) {
        def result = [status: 'OK']
        def status = scriptService.runScript(jobName, [action: 'status'])
        result.jobStatus = status
        render result as JSON
    }
    
    private doGetJobStatus(String jobName) {
        return scriptService.runScript(jobName, [action:'status'])
    }

    def runNow(String jobName) {
        scriptService.runScript(jobName, [action:'runNow'])
        redirect(action: "list")
    }

    def startScheduler() {
        quartzScheduler.start()
        redirect(action: "list")
    }

    def stopScheduler() {
        quartzScheduler.standby()
        redirect(action: "list")
    }

}
