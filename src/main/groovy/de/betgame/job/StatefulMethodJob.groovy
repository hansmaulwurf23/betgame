package de.betgame.job

@org.quartz.DisallowConcurrentExecution
class StatefulMethodJob extends InvokeMethodJob {
    
    public StatefulMethodJob() { }
    
}
