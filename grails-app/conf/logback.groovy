import ch.qos.logback.classic.sift.*
import ch.qos.logback.core.rolling.*
import grails.util.Metadata
import org.springframework.boot.ApplicationPid
import ch.qos.logback.core.util.FileSize

// Mimic Spring Boot logging configuration.
conversionRule 'clr', org.springframework.boot.logging.logback.ColorConverter
conversionRule 'wex', org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter

// Get PID for Grails application.
// We use it in the logging output.
if (!System.getProperty("PID")) {
    System.setProperty("PID", (new ApplicationPid()).toString())
}

def appName = Metadata.current.getApplicationName()

def maxSize = FileSize.valueOf('200MB')
def history = 60

def logDir =  "/var/log/rrzepp/${appName}"

def encoderPattern = '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} ' + // Date
    '%clr([%X{ipstr}|%X{username}]){faint} ' + //MDC
    '%clr(%5p) ' + // Log level
    //'%clr(%property{PID}){magenta} ' + // PID
    //'%clr([%t]){faint} ' + // Thread
    //'%clr([%X{class}|%X{id}] [%X{entity}]){faint} ' + //MDC
    '%clr(%logger{0}){cyan} %clr(:){faint} ' + // Logger
    '%m%n%wex' // Message


/* #####################  APPENDERS ####################################### */


// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) { pattern = encoderPattern }
}

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('FILE', RollingFileAppender) {
    file = "${logDir}/${appName}.log"
    encoder(PatternLayoutEncoder) { pattern = encoderPattern }
    rollingPolicy(SizeAndTimeBasedRollingPolicy){
        fileNamePattern= "${logDir}/${appName}.%d{yyyy-MM-dd}.%i.log"
        maxFileSize = maxSize
        maxHistory = history
    }
}

/*
appender('LOGHOST', GelfAppender) {
	filter = new ThresholdFilter([level: Level.INFO])
	server = 'idm-donbot.rrze.uni-erlangen.de'
	port = 5141
	additionalFields = "application=${appName}"
}
*/
def isSiftAppend = true;

appender("SIFT", SiftingAppender) {
    discriminator(MDCBasedDiscriminator) {
        key = "logFileName"
        defaultValue = "mdc"
    }
    appenderFactory =
        [buildAppender : {context, discriminatingValue ->
            RollingFileAppender appender = new RollingFileAppender();
            try{
                appender.setContext(context);
                appender.setName("logger:${discriminatingValue}");
                def file = "${logDir}/${appName}.${discriminatingValue}.log"
                appender.setFile(file);
                
                def encoder = new ch.qos.logback.classic.encoder.PatternLayoutEncoder()
                encoder.context = context
                encoder.pattern = encoderPattern
                appender.setEncoder(encoder);
                
                def policy = new ch.qos.logback.core.rolling.TimeBasedRollingPolicy()
                policy.setParent(appender);
                policy.setContext(context)
                
                def triggerPolicy = new ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP()
                triggerPolicy.maxFileSize = maxSize
                triggerPolicy.setContext(context)
                
                def policyFileNamePattern= "${logDir}/${appName}.${discriminatingValue}.%d{yyyy-MM-dd}.%i.log"
                policy.fileNamePattern= policyFileNamePattern
                policy.maxHistory = history
                policy.timeBasedFileNamingAndTriggeringPolicy = triggerPolicy
                
                appender.setRollingPolicy(policy);
                appender.setAppend(isSiftAppend);
                encoder.start();
                policy.start();
                triggerPolicy.start();
                appender.start();
            }catch(e){
                println e.getMessage()
            }
            
            return appender
        }] as
            ch.qos.logback.core.sift.AppenderFactory
}


//def usedAppenders = ['STDOUT', 'FILE', 'SIFT', 'LOGHOST']
def usedAppenders = ['STDOUT', 'FILE']

logger('org.grails', ERROR, usedAppenders, false)
logger('org.springframework', ERROR, usedAppenders, false)
logger('org.apache', ERROR, usedAppenders, false)
logger('org.hibernate', ERROR, usedAppenders, false)
logger('net.sf', ERROR, usedAppenders, false)
logger('grails.plugin.gscripting',  DEBUG, usedAppenders, false)
logger('grails.app.services.grails.plugin.gscripting',  INFO, usedAppenders, false)
logger('grails.app.controllers.de.rrze.mail',  DEBUG, usedAppenders, false)
logger('grails.app.services', DEBUG, usedAppenders, false)
logger('grails.app.controllers.gmail',  DEBUG, usedAppenders, false)
logger('org.grails.plugin.encore', ERROR, usedAppenders, false)
logger('org.grails.web.mapping', ERROR, usedAppenders, false)

//root(DEBUG, usedAppenders)
root(DEBUG, usedAppenders)