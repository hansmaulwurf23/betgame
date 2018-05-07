package de.betgame

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_MAILADMIN'])
@Transactional
class ScriptController {

    def scriptService
    def springSecurityService

    def index() {
        redirect(action:'editor')
    }

    def editor() {
        def sl = [] as Set
        if (params.scriptNames) {
            sl.addAll(params.scriptNames)
        }

        [scriptNames:sl]
    }

    def deleteFile(String name) {
        def result = scriptService.removeScript(name)

        if (result) {
            render([status:"Script ${params.id} was success removed.", result:result] as JSON)
        } else {
            render([status: "error", msg: "Cannot find script with id: ${params.id}"] as JSON)
        }
    }

    def listFiles() {
        def scripts = scriptService.listScripts()
        def mappedScripts = scripts.collect{[id:it.id, name: it.name, folder: it.folder]}

        render ([status:"success", result: mappedScripts] as JSON)
    }

    def loadFile(String name) {
        def script = scriptService.getScript(name)
        if (script) {
            render ([status:"success", result:script] as JSON)
        } else
            render ([status: "error", msg: "Cannot find script with id: ${params.id}"] as JSON)
    }

    def saveFile() {
        Script script = new Script()

        script.content = params['content']
        script.name = params['name']
        script.mode = params['mode'] ? params['mode'] : null
        script.folder = params['folder']
        script.author = params['author']
        script.dsl = params['dsl'] ? params['dsl'] : null
        script.status = params['status']

        def result = scriptService.saveScript(script, springSecurityService.isLoggedIn() ? springSecurityService.authentication.principal.username: '[na]')

        scriptService.reloadScript(script.name)

        log.info "Reloading ${script.name}"

        if (result) {
            render([status: "success", msg: "Script ${params.name} was successfully saved", newId:script.name] as JSON)
        } else {
            render([status: "error", msg: "Cannot save script:${params.name}"] as JSON)
        }
    }

    def reload(String name) {
        scriptService.reloadScript(name)
        render ([status: "success", msg: "Script:${name} was successfully reloaded."] as JSON)
    }

    def run(String name) {
        scriptService.runScript(name, params)
        render ([status: "success", msg: "Script ${name} was successfully executed"] as JSON)
    }

    def loadVersions(String name) {
        def versions = [[value:'local', text:'local']]

        try {
            versions.addAll(scriptService.getScriptVersions(name)?.reverse()?.collect { [value: it, text: 'v. ' + it] })
        } catch (e) {}

        if (versions) {
            render([status: "success", result: versions] as JSON)
        } else {
            render([status: "error", msg: "Cannot find script with id: ${name}"] as JSON)
        }
    }

    def loadVersion(String name, Long version) {
        def script = scriptService.getScriptWithMeta(name, version)

        if (script) {
            render([status:"success", result:script] as JSON)
        } else {
            render([status: "error", msg: "Cannot find script with name: ${name}"] as JSON)
        }
    }

}
