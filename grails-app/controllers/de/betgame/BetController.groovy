package de.betgame


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

/**
 * BetController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
class BetController {

	def springSecurityService
	
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Bet.list(params), model:[betInstanceCount: Bet.count()]
    }

	def list(Integer max) {
		println params
        params.max = Math.min(max ?: 10, 100)
        respond Bet.list(params), model:[betInstanceCount: Bet.count()]
    }

    def show(Bet betInstance) {
        respond betInstance
    }

    def create() {
		def bet = new Bet(params)
        respond bet
    }

    @Transactional
    def save() {
		Bet betInstance = new Bet(params)
		betInstance.user = springSecurityService.currentUser
		log.warn "___________________${betInstance.user}"
		
        if (betInstance == null) {
            notFound()
            return
        }

        if (betInstance.hasErrors()) {
            respond betInstance.errors, view:'create'
            return
        }

		betInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'betInstance.label', default: 'Bet'), betInstance.id])
                redirect betInstance
            }
            '*' { respond betInstance, [status: CREATED] }
        }
    }

    def edit(Bet betInstance) {
        respond betInstance
    }

    @Transactional
    def update(Bet betInstance) {
        if (betInstance == null) {
            notFound()
            return
        }

        if (betInstance.hasErrors()) {
            respond betInstance.errors, view:'edit'
            return
        }

        betInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Bet.label', default: 'Bet'), betInstance.id])
                redirect betInstance
            }
            '*'{ respond betInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Bet betInstance) {

        if (betInstance == null) {
            notFound()
            return
        }

        betInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Bet.label', default: 'Bet'), betInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'betInstance.label', default: 'Bet'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
