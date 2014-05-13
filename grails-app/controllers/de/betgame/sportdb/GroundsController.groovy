package de.betgame.sportdb


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * GroundsController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class GroundsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def beforeInterceptor = {
		if (!session.event) {
			session.event = Events.findByKey(grailsApplication.config.de.betgame.sportdb.event.key)
		}
	}
	
	def index(Integer max) {
        redirect(action:'list')
    }

	def list(Integer max) {
		def grounds = EventsGrounds.findAllByEvent(session.event)*.ground.sort { it.title }
        [groundsInstanceList: grounds]
    }

    def show(Grounds groundsInstance) {
        respond groundsInstance
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'groundsInstance.label', default: 'Grounds'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
