package de.betgame

import grails.plugin.gscripting.GscriptingService
import grails.transaction.Transactional
import groovy.sql.Sql

@Transactional
class ScriptService {

    GscriptingService gscriptingService

    def removeScript(String name) {
        def scripts = Script.findAllByName(name)
        if (scripts) {
           scripts.each { it.delete(flush:true) }
        } else {
            return false
        }
    }

    def listScripts() {
        Script.withSession { session ->
            Sql sql = new Sql(session.connection())
            def scriptIDs =  sql.rows("""
                select s.script_id as id
                from scripts s
                where version = (select max(version) from scripts t where t.name = s.name)
            """)*.id

            Script.getAll(scriptIDs)
        }
    }

    def getScript(String name, Long version = null) {
        getScriptWithMeta(name, version)
    }

    def runScript(String name, callParams = [:], state = [:]) {
        registerScript(name)
        return gscriptingService.run(name, callParams, state)
    }

    def getScriptVersions(String name) {
        Script.findAllByName(name)*.version?.sort()
    }

    def reloadScript(String name) {
        registerScript(name, true)
    }

    def saveScript(Script script, modifier='[na]'){
        script.author = modifier
        script.created = new Date()

        // get current instance
        def currentInstance = getScriptWithMeta(script.name)
    
        if (!currentInstance) {
            // init new instance
            script.version = 1
        } else {
            
            if (script.properties == currentInstance.properties) {
                log.info "No changes to ${script.name}; skipping save"
                return currentInstance
            }
            script.version = currentInstance.version + 1
        }
        
        script.save(flush:true)
        return script
    }


    def readFolder(String folder) {
        Script.withSession { session ->
            Sql sql = new Sql(session.connection())
            def scriptIDs =  sql.rows("""
                select s.script_id as id
                from scripts s
                where version = (select max(version) from scripts t where t.name = s.name)
                  and folder ilike '%/${folder}/%'
            """.toString())*.id
        
            Script.getAll(scriptIDs)
        }
    }

    def registerScript(String name, force = false) {
        if(!gscriptingService.scriptRuntimeEnvs[name] || force){
            Script script = getScript(name)
            if (script) {
                gscriptingService.registerScriptRuntimeEnv(script.name, script.content, script.dsl ?: 'default')
                log.info "Registering script with name: ${name}"
            }else{
                log.error "Cannot find script with name: ${name}"
            }
        }
    }

    def unregisterScript(String name) {
        gscriptingService.unregisterScriptRuntimeEnv(name)
    }
    
    def getScriptWithMeta(String name, Long version = null) {
        def instance = null
        if (name) {
            if (version) {
                instance = Script.findByNameAndVersion(name, version)
            } else {
                def existingScripts = Script.findAllByName(name)
                if (existingScripts && existingScripts.size() > 0) {
                    instance = existingScripts.sort { it.version }.last()
                }
            }
        }
        return instance
    }
    
    def registerDsl(providerScript) {
        log.info "Registering:  ${providerScript.name} with dsl: ${providerScript.dsl}"
        gscriptingService.registerDslProviderScriptRuntimeEnv(providerScript.name, providerScript.content, providerScript.dsl)
    }
    
    
    
}
