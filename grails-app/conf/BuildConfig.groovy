grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.project.war.file = "target/${appName}.war"

//grails.server.port.http = 8088


grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
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
		mavenRepo "http://mvnrepository.com/artifact/"
		mavenRepo 'http://repo.spring.io/milestone'
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        // runtime 'mysql:mysql-connector-java:5.1.24'
		runtime 'postgresql:postgresql:8.4-702.jdbc3'
		runtime 'org.xerial:sqlite-jdbc:3.7.15-M1'
    }

    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.50"

		// use once on project start:
		//compile ":kickstart-with-bootstrap:1.1.0"
		compile ":asset-pipeline:1.8.11"
		compile ":less-asset-pipeline:1.7.0"
		compile ":twitter-bootstrap:3.1.1.3"
		
		compile ":ws-client:1.0"
		
		//compile ":db-reverse-engineer:0.5"
		
		//compile ":spring-security-core:2.0-RC2"
		//compile ":spring-security-cas:2.0-RC1"
		runtime ":spring-security-core:1.2.7.3"
		runtime ":spring-security-cas:1.0.5"
		//runtime ":webxml:1.4.1"
		
        // plugins for the compile step
        compile ":scaffolding:2.0.1"
        compile ':cache:1.1.1'
		
		compile ":quartz:1.0.1"
		compile ":quartz-monitor:1.0"

        // plugins needed at runtime but not for compilation
        runtime ":hibernate:3.6.10.7" // or ":hibernate4:4.1.11.1"
        //runtime ":database-migration:1.3.8"
        runtime ":jquery:1.10.2.2"
    }
}
