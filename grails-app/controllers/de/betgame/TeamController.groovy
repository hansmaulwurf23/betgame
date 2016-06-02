package de.betgame

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_EMPLOYEE_191', 'ROLE_STUDENTASSISTENT_191'])
class TeamController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def springSecurityService
	
	def beforeInterceptor = { }
	
	def index() {
        redirect(action:'list')
    }

	def list(Integer max) {
		def teams = Team.list(sort:'name')
		def games = Game.list()
		def groupMap = (games.collectEntries( { [it.team1.code, it.groupName] } ) + games.collectEntries( { [it.team2.code, it.groupName] } ))
		[teamsInstanceList :  teams, groupMap : groupMap]
    }

    def show(Team teamInstance) {
		def games = Game.findAllByTeam1OrTeam2(teamInstance, teamInstance, [sort:'playAt'])
		def myBets = Bet.findAllByGameInListAndUser(games, springSecurityService.currentUser).collectEntries { [it.game, it] }
        respond teamInstance, model: [games:games, myBets:myBets]
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'teamInstance.label', default: 'Team'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
