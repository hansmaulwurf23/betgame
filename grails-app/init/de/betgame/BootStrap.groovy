package de.betgame

class BootStrap {

    def scriptService
    
    def init = { servletContext ->
        scriptService.runScript('init')
    }
    def destroy = {
    }
}
