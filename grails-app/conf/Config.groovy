// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}
 
grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// reverse engineering
grails.plugin.reveng.packageName = "de.betgame.sportdb"

// bootstrap stuff
grails.plugins.twitterbootstrap.fixtaglib = true
grails.plugins.twitterbootstrap.defaultBundle = 'bundle_bootstrap'

grails.assets.excludes = ["**/*.less"]
grails.assets.includes = ["**/application.less"]

grails.assets.less.compiler = 'less4j'

grails.assets.minifyJs = false
grails.assets.minifyCss = false

// set per-environment serverURL stem for creating absolute links
environments {
		production {
				grails.config.locations = ["file:/var/opt/betgame/betgame-config.groovy"]
		}
		development {
				grails.config.locations = ["file:${userHome}/.grails/betgame-config.groovy"]
		}
}

environments {
    development {
        grails.logging.jul.usebridge = true
		grails.plugin.springsecurity.debug.useFilter = false
    }
    production {
        grails.logging.jul.usebridge = false
    }
}

// log4j configuration
log4j = {
    appenders {
		console name: 'stdout',
			layout:new util.log4j.ANSIPatternLayout(conversionPattern: '%d{yyyyMMdd_HHmmss,SSS} [%X{username}] %5p %c{1} - %m%n')

		rollingFile name: 'file',
			file: "/var/log/grails/betgame.log",
			layout:new util.log4j.ANSIPatternLayout(conversionPattern: '%d{yyyyMMdd_HHmmss,SSS} [%X{username}] %5p %c{1} - %m%n'),
			maxFileSize:"2000MB",
			maxBackupIndex: 10
			
		appender new org.apache.log4j.net.SMTPAppender(
				name: "smtpAppender",
				layout:pattern(conversionPattern: '%d{yyyy-MM-dd HH:mm:ss,SSSS} [%X{appVersion}] [%X{username}] %5p %c{1} - %X{marker} %m%n'),
				threshold: error,
				subject:"[BETGAME ERROR] liam::Log4j message",
				to: "martin.fischer@fau.de, oliver.maurer@fau.de",
				from: "no-reply@rrze.fau.de",
				smtpHost: "smtp.uni-erlangen.de",
				bufferSize: 10
		)
    }
	
	debug  'grails.app',
			'de.betgame'
			//'org.springframework.security'

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
		   
    warn    'org.apache.cxf'
		   
	root { info 'file', 'smtpAppender'}
	
	environments {
		development {
			root { info 'stdout', 'file'}
		}
	}
}




// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'de.betgame.sec.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'de.betgame.sec.UserRole'
grails.plugins.springsecurity.authority.className = 'de.betgame.sec.Role'
grails.plugins.springsecurity.securityConfigType = grails.plugins.springsecurity.SecurityConfigType.Annotation


openligadb.soap.url="http://www.OpenLigaDB.de/Webservices/Sportsdata.asmx"

cxf {
	client {
		openligaDBServiceClient {
			//used in wsdl2java
			//namespace = "cxf.client.openligadb"
			//client = false //defaults to false
			//bindingFile = "grails-app/conf/bindings.xml"
			//outputDir = "src/java"
			//allowChunking = true //false

			//used for invoking service
			clientInterface = de.msiggi.sportsdata.webservices.SportsdataSoap
			serviceEndpointAddress = "${openligadb.soap.url}"
			wsdl = "${openligadb.soap.url}?WSDL"
		}
	}
}
