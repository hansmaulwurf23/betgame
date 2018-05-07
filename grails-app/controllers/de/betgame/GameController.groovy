package de.betgame

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional;

@Secured(['ROLE_EMPLOYEE_191', 'ROLE_STUDENTASSISTENT_191'])
class GameController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def springSecurityService
	def openLigaDBService
	
	def index() {
        redirect(action:'list')
    }

	def list(String group, String phase) {
		def games
		
		if (!phase) {
			games = Game.list([sort:'playAtUTC'])
		} else {
			games = Game.findAllByPhase(phase, [sort:'playAtUTC'])
		}
		
		def phases = Game.executeQuery("select phase from Game order by playAtUTC")?.unique()
		
		def user = springSecurityService.currentUser
		def gameIDsFromBets = Bet.executeQuery("select b.game.id as gameID from Bet b where b.user = :u", [u:user])
		
        [games:games, phases:phases, group:group, phase:phase, gameIDsFromBets:gameIDsFromBets]
    }

    def show(Game gameInstance) {
		def user = springSecurityService.currentUser
		def myBet
		log.info "Looking for bet from user ${user.username} and game ${gameInstance}"
		if (user) {
			myBet = Bet.findByUserAndGame(user, gameInstance)
		}
        [gameInstance: gameInstance, myBet: myBet]
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
	
	@Secured(['ROLE_IDMADMIN'])
	@Transactional
	def forceFetch(Game gameInstance) {
		openLigaDBService.updateGameScore(gameInstance.id, true)
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
