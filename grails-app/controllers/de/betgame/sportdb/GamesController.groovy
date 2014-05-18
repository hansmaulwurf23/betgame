package de.betgame.sportdb


import static org.springframework.http.HttpStatus.*
import de.betgame.Bet;
import grails.transaction.Transactional

/**
 * GamesController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class GamesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def springSecurityService
	
	def beforeInterceptor = { 
		if (!session.event) {
			session.event = Events.findByKey(grailsApplication.config.de.betgame.sportdb.event.key)
		}
	}

	def index() {
		log.warn springSecurityService.authentication
        redirect(action:'list')
    }

	def list(Integer groupId) {
		def event = session.event
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
		def user = springSecurityService.currentUser
		def myBet
		if (user) {
			myBet = Bet.findByUserAndGameid(user, gamesInstance.id)
		}
        respond gamesInstance, [myBet:myBet]
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
