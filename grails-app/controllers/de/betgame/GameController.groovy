package de.betgame

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.sql.Sql;

@Secured(['ROLE_EMPLOYEE_191', 'ROLE_STUDENTASSISTENT_191'])
class GameController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def springSecurityService
	def openLigaDBService
	
	def dataSource
	
	def index() {
        redirect(action:'list')
    }

	def list(String group, String phase) {
		Sql sql = new Sql(dataSource)
		String query = """
			select game_id from game g where 1=1
			${phase ? " and phase = '$phase' " : ''}
			${group ? " and team1_id in (select team_id from team where group_name = '$group') and team2_id in (select team_id from team where group_name = '$group')" : ''}
		""".toString()
		
		def gameIDs = sql.rows(query)
		
		def games = Game.getAll(gameIDs*.game_id)
		def phases = Game.executeQuery("select phase from Game order by playAtUTC")?.unique()
		def groups = Team.executeQuery("select distinct groupName from Team order by groupName")
		
		def user = springSecurityService.currentUser
		def gameIDsFromBets = Bet.executeQuery("select b.game.id as gameID from Bet b where b.user = :u", [u:user])
		
        [games:games, phases:phases, groups:groups, group:group, phase:phase, gameIDsFromBets:gameIDsFromBets]
    }

    def show(Game game) {
		def user = springSecurityService.currentUser
		def myBet
		log.info "Looking for bet from user ${user.username} and game ${game}"
		if (user) {
			myBet = Bet.findByUserAndGame(user, game)
		}
        [game: game, myBet: myBet]
    }
	
	def edit(Game game) {
		[game:game]
	}

	@Secured(['ROLE_IDMADMIN'])
	@Transactional
	def update(Game game) {
		if (game.playAt > new Date()) {
			flash.message = "Das Spiel hat noch nichtmal angefangen!"
			game.discard()
		} else {
			game.save(flush:true, failOnError:true)
		}

		redirect(action:'show', params:[id:game.id])
	}
	
	@Secured(['ROLE_IDMADMIN'])
	@Transactional
	def forceFetch(Game game) {
		openLigaDBService.updateGameScore(game.id, true)
		redirect(action:'show', params:[id:game.id])
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
