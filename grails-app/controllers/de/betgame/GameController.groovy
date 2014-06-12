package de.betgame

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_EMPLOYEE_191'])
class GameController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def springSecurityService
	
	def beforeInterceptor = { }

	def index() {
        redirect(action:'list')
    }

	def list(String group) {
		// FIXME nonbrainer
		if (!group) {
			group = 'A'
		}
		def games = Game.findAllByGroupName(group, [sort:'playAtUTC'])
		def groups = Game.executeQuery("select distinct groupName from Game order by groupName")
        [gameInstanceList:games, groups:groups, group:group, layout_nosecondarymenu:true]
    }

    def show(Game gameInstance) {
		def user = springSecurityService.currentUser
		def myBet
		log.info "Looking for bet from user ${user} and game ${gameInstance}"
		if (user) {
			myBet = Bet.findByUserAndGame(user, gameInstance)
		}
        respond gameInstance, model: [myBet:myBet]
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'gameInstance.label', default: 'Game'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
