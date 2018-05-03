package de.betgame

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean
import org.springframework.context.EnvironmentAware
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.grails.io.support.PathMatchingResourcePatternResolver
import org.grails.core.exceptions.GrailsConfigurationException
import org.grails.config.*
import org.springframework.core.env.*
import grails.util.*




@Slf4j
class Application extends GrailsAutoConfiguration implements EnvironmentAware {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }
    
    @Override
    void setEnvironment(Environment environment) {
        
        def resourcePatternResolver = new PathMatchingResourcePatternResolver()
        
        def configLocations = environment.getProperty('grails.config.locations')?.split(',') as List?:[]
        
        def configUrls = []
        configLocations.each {
            def urls = resourcePatternResolver.getResources(it).collect{it.getURL()}
            if (urls) {
                configUrls.addAll(urls)
            } else  {
                log.warn "no configs found with pattern ${it}"
            }
        }
        
        configUrls.each { URL configURL ->
//          URL configURL = (configLocation instanceof URL) ? configLocation: new URL(configLocation)
            def configLocation = configURL.path //configLocation.toString()
            Resource resourceConfig = new UrlResource(configURL)
            if(resourceConfig.exists()) {
                if(configLocation.endsWith('.groovy')) {
                    environment.propertySources.addFirst(loadExternalGroovyConfig(environment, configLocation, resourceConfig))
                    log.info "Loaded external config ${configLocation}"
                }
                else if(configLocation.endsWith('.yml') || configLocation.endsWith('.yaml')) {
                    YamlPropertiesFactoryBean ypfb = new YamlPropertiesFactoryBean()
                    ypfb.setResources(resourceConfig)
                    ypfb.afterPropertiesSet()
                    Properties properties = ypfb.getObject()
                    environment.propertySources.addFirst(new PropertiesPropertySource(configLocation, properties))
                    log.info "Loaded external config ${configLocation}"
                }
                else {
                    log.warn "Don't know how to load external config ${configLocation} (not .groovy nor .yml)"
                }
            } else {
                log.warn("External config $configLocation not found!")
            }
        }
    }
    
    
    def loadExternalGroovyConfig(Environment environment, String name, Resource resource){
        if (resource.exists()) {
            
            
            def configSlurper= new ConfigSlurper(grails.util.Environment.current.name?:'')
            def configMap = [
                userHome: System.getProperty('user.home'),
                grailsHome: BuildSettings.GRAILS_HOME?.absolutePath,
                springProfile: null,
                appName: Metadata.getCurrent().getApplicationName(),
                appVersion: Metadata.getCurrent().getApplicationVersion(),
            ]
            if (environment instanceof ConfigurableEnvironment){
                PropertySourcesConfig propertySourcesConfig = new PropertySourcesConfig(environment.propertySources)
                configMap.put('parentConfig', propertySourcesConfig.flatten())
            }
            
            configSlurper.setBinding(configMap)
            
            try {
                def configObject = configSlurper.parse(resource.URL)
                def propertySource = new NavigableMap()
                propertySource.merge(configObject, false)
                
                return new NavigableMapPropertySource(name, propertySource)
            } catch (Throwable e) {
                log.error("Unable to load $resource.filename: $e.message", e)
                throw new GrailsConfigurationException("Error loading $resource.filename due to [${e.getClass().name}]: $e.message", e)
            }
        }
        return null
        
    }
    
}