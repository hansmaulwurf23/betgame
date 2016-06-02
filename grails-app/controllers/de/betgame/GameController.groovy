package de.betgame

import grails.plugins.springsecurity.Secured
import grails.transaction.Transactional;

@Secured(['ROLE_EMPLOYEE_191', 'ROLE_STUDENTASSISTENT_191'])
class GameController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def springSecurityService
	
	def beforeInterceptor = { }

	def index() {
        redirect(action:'list')
    }

	def list(String group, String phase) {
		def games
		if (Game.countByPlayAtGreaterThanAndPhase(new Date(), 'Vorrunde') == 0) {
			phase = phase ?: 'KO'
		}
		if (!phase) {
			games = !group ? Game.list([sort:'playAtUTC']) : Game.findAllByGroupName(group, [sort:'playAtUTC'])
		} else {
			games = Game.findAllByPhaseNot('Vorrunde', [sort:'playAtUTC'])
		}
		def groups = Game.executeQuery("select distinct groupName from Game where groupName is not null order by groupName")
        [gameInstanceList:games, groups:groups, group:group, layout_nosecondarymenu:true, phase:phase]
    }

    def show(Game gameInstance) {
		def user = springSecurityService.currentUser
		def myBet
		log.info "Looking for bet from user ${user.username} and game ${gameInstance}"
		if (user) {
			myBet = Bet.findByUserAndGame(user, gameInstance)
		}
        respond gameInstance, model: [myBet:myBet]
    }
	
	def edit(Game gameInstance) {
		[gameInstance:gameInstance]
	}

	@Secured(['ROLE_IDMADMIN'])
	@Transactional
	def update(Game gameInstance) {
		if (gameInstance.playAt > new Date()) {
			flash.message = "Das Spiel hat noch nichtmal angefangen!"
			gameInstance.discard()
		} else {
			gameInstance.save(flush:true, failOnError:true)
		}

		redirect(action:'show', params:[id:gameInstance.id])
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
