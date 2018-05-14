package de.betgame

import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_EMPLOYEE_191', 'ROLE_STUDENTASSISTENT_191'])
@Transactional(readOnly = true)
class LocationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index() {
        redirect(action:'list')
    }

	def list() {
        [locations: Location.list()]
    }

    def show(Location location) {
        [location:location]
    }

}
