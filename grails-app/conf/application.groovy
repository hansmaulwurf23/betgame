/*
 * =================================================================================================================
 *    SPRING SECURITY CONFIGURATION
 * =================================================================================================================
 */

server.contextPath = "/${appName}"
server.port =  8080
grails.serverURL = "https://liam.rrze.uni-erlangen.de/${appName}"
// set per-environment serverURL stem for creating absolute links

environments {
    development {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
    test {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
    
}

/*
 * =================================================================================================================
 * 			EXTERNAL CONFIGURATION
 * =================================================================================================================
 */

if(!grails.config.locations || !(grails.config.locations instanceof List)) {
    grails.config.locations = []
}
environments {
    development {
//        grails.config.locations << 'classpath*:*dev-config.groovy'
        grails.config.locations << "file:${userHome}/.grails/${appName}-config.groovy"
    }
}

grails.config.locations << 'classpath*:*-config.groovy'
grails.config.locations << "file:/opt/rrzepp/apps/${appName}/internal/${appName}-config.groovy"
grails.config.locations << "classpath*:*secrets.groovy"

/*
 * =================================================================================================================
 * 			SPRING SECURITY
 * =================================================================================================================
 */

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'de.betgame.sec.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'de.betgame.sec.UserRole'
grails.plugin.springsecurity.userLookup.authority.className = 'de.betgame.sec.Role'

grails.plugin.springsecurity.cas.loginUri = '/login'
grails.plugin.springsecurity.cas.artifactParameter = 'ticket'
grails.plugin.springsecurity.cas.serviceParameter = 'service'
grails.plugin.springsecurity.cas.serviceUrl = "${grails.serverURL}/login/cas"

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
    [pattern: '/',               access: ['permitAll']],
    [pattern: '/error',          access: ['permitAll']],
    [pattern: '/index',          access: ['permitAll']],
    [pattern: '/index.gsp',      access: ['permitAll']],
    [pattern: '/shutdown',       access: ['permitAll']],
    [pattern: '/assets/**',      access: ['permitAll']],
    [pattern: '/layoutDemo/**',  access: ['permitAll']],
    [pattern: '/**/js/**',       access: ['permitAll']],
    [pattern: '/**/css/**',      access: ['permitAll']],
    [pattern: '/**/images/**',   access: ['permitAll']],
    [pattern: '/**/fonts/**',    access: ['permitAll']],
    [pattern: '/**/favicon.ico', access: ['permitAll']],
    [pattern: '/home/**',        access: ['permitAll']],
]

grails.plugin.springsecurity.filterChain.chainMap = [
    [pattern: '/assets/**',      filters: 'none'],
    [pattern: '/**/js/**',       filters: 'none'],
    [pattern: '/**/css/**',      filters: 'none'],
    [pattern: '/**/fonts/**',      filters: 'none'],
    [pattern: '/**/images/**',   filters: 'none'],
    [pattern: '/**/favicon.ico', filters: 'none'],
    [pattern: '/**',             filters: 'JOINED_FILTERS']
]

/*
 * =================================================================================================================
 * 			CXF WEBSERVICE
 * =================================================================================================================
 */

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
