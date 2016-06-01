grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.project.war.file = "target/${appName}.war"

grails.tomcat.nio = true
grails.tomcat.scan.enabled = true


grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: false,
    // configure settings for the run-war JVM
    war: false,
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve true // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

		grailsPlugins()
		grailsHome()
		mavenLocal()
		grailsCentral()
		mavenCentral()
		// uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
		mavenRepo "http://repository.codehaus.org"
		mavenRepo "http://download.java.net/maven/2/"
		mavenRepo "http://repository.jboss.com/maven2/"
		
		mavenRepo "http://repo.grails.org/grails/repo/"
		
		// for Jasper
		mavenRepo "http://jasperreports.sourceforge.net/maven2/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        // runtime 'mysql:mysql-connector-java:5.1.24'
		runtime 'org.postgresql:postgresql:9.3-1101-jdbc41'
    }

    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.55.3"

		// use once on project start:
		//compile ":kickstart-with-bootstrap:1.1.0"
		compile ":asset-pipeline:2.6.10"
		compile ":less-asset-pipeline:2.6.7"
		
		//compile ":ws-client:1.0"
		compile ":cxf-client:2.1.2"
		
		//compile ":spring-security-core:2.0-RC2"
		//compile ":spring-security-cas:2.0-RC1"
		runtime ":spring-security-core:1.2.7.4"
		runtime ":spring-security-cas:1.0.5"
		//runtime ":webxml:1.4.1"
		
        // plugins for the compile step
        compile ":scaffolding:2.1.2"
        compile ':cache:1.1.8'
		
		compile ":quartz:1.0.2"
		//compile ":quartz-monitor:1.0"
		compile ":mail:1.0.7"

        // plugins needed at runtime but not for compilation
        runtime ":hibernate:3.6.10.18"
    }
}
