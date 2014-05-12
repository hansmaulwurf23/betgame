package de.betgame.sportdb


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * TeamsController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class TeamsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def beforeInterceptor = {
		if (!session.event) {
			session.event = Events.findByKey('world.2014')
		}
	}
	
	def index() {
        redirect(action:'list')
    }

	def list(Integer max) {
		def teams = EventsTeams.findAllByEvent(session.event)*.team.sort { it.code }
		def groupMap = [:]
		def availGroups = Groups.findAllByEvent(session.event)
		log.warn availGroups
		teams.each { t ->
			groupMap[t.code] = GroupsTeams.findByTeamAndGroupInList(t, availGroups).group
		}
		[teamsInstanceList :  teams, groupMap : groupMap]
    }

    def show(Teams teamsInstance) {
        respond teamsInstance
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'teamsInstance.label', default: 'Teams'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
