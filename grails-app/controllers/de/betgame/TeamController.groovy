package de.betgame

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_EMPLOYEE_191', 'ROLE_STUDENTASSISTENT_191'])
@Transactional
class TeamController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def springSecurityService
	
	def index() {
        redirect(action:'list')
    }

	def list() {
		def teams = Team.findAllByCodeIsNotNull(sort:'name')
		[teams: teams]
    }

    def show(Team team) {
		def games = Game.findAllByTeam1OrTeam2(team, team, [sort:'playAt'])
		def myBets = Bet.findAllByGameInListAndUser(games, springSecurityService.currentUser).collectEntries { [it.game, it] }
        [team: team, games:games, myBets:myBets]
    }

}
