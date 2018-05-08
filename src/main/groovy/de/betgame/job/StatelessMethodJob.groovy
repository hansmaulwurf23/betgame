package de.betgame.job

/**
 * Quartz Job implementation that invokes a specified method of an object, or static if class is specified.
 * this has the DisallowConcurrentExecution set to true
 */
class StatelessMethodJob extends InvokeMethodJob {
    
    public StatelessMethodJob() { }
}
