package de.betgame


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_EMPLOYEE_191', 'ROLE_STUDENTASSISTENT_191'])
@Transactional(readOnly = true)
class LocationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Location.list(params), model:[locationInstanceCount: Location.count()]
    }

	def list(Integer max) {
		println params
        params.max = Math.min(max ?: 10, 100)
        respond Location.list(params), model:[locationInstanceCount: Location.count()]
    }

    def show(Location locationInstance) {
        respond locationInstance
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'locationInstance.label', default: 'Location'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
