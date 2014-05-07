package de.betgame.sportdb


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * GamesController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class GamesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index() {
		if (!session.event) {
			session.event = Events.findByKey('world.2014')
			log.warn "Setting session.event to ${session.event}"
		} else {
			log.debug "session.event is ${session.event}"
		}
        redirect(action:'list')
    }

	def list(Integer max, Integer groupId) {
		def event = Events.findByKey('world.2014')
		def group
		if (!groupId) {
			group = Groups.findAllByEvent(event).sort { it.pos }[0]
		} else {
			group = Groups.get(groupId)
		}
		
		log.warn group
        
		def games = Games.findAllByGroup(group)
		println games
        [gamesInstanceList:games, groups:Groups.findAllByEvent(event), group:group, layout_nosecondarymenu:true]
    }

    def show(Games gamesInstance) {
        respond gamesInstance
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'gamesInstance.label', default: 'Games'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
