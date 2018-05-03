package de.betgame


import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

/**
 * BetController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
@Transactional(readOnly = true)
@Secured(['ROLE_EMPLOYEE_191', 'ROLE_STUDENTASSISTENT_191'])
class BetController {

	def springSecurityService
	
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
        redirect(action:'list')
    }

	def list() {
		params.sort = 'game.playAt'
		def bets = Bet.findAllByUser(springSecurityService.currentUser, params)
		def games
		if (bets) {
			games = Game.findAllByIdInList(bets*.game*.id).collectEntries { [it.id, it] }
		}
		[betInstanceList: bets, games:games]
    }

    def show(Bet betInstance) {
		if (betInstance.user != springSecurityService.currentUser) {
			notYours()
			return
		}
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
		
		if (cheating(betInstance)) {
			flash.message = "Sehr sportlich, Herr Kollege... Ihr Versuch zu Bescheissen wurde protokolliert und wird morgen in der Kaffeekueche ausgehaengt..."
			betInstance.discard()
			redirect(controller:'home', action:'index')
		} else {
			
	        if (betInstance == null) {
	            notFound()
	            return
	        }
	
	        if (betInstance.hasErrors()) {
	            respond betInstance.errors, view:'create'
	            return
	        }
			
			def otherBets = Bet.findAllByUserAndGame(springSecurityService.currentUser, betInstance.game)
			if (otherBets) {
				alreadyBet()
				return
			}
	
			betInstance.save(flush:true, failOnError:true)
	
	        request.withFormat {
	            form {
	                flash.message = message(code: 'default.created.message', args: [message(code: 'betInstance.label', default: 'Bet'), betInstance.id])
	                redirect betInstance
	            }
	            '*' { respond betInstance, [status: CREATED] }
	        }
		}
    }

    def edit(Bet betInstance) {
		if (betInstance.user != springSecurityService.currentUser) {
			notYours()
			return
		}
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
		
		if (betInstance.user != springSecurityService.currentUser) {
			notYours()
			return
		}
		
		if (cheating(betInstance)) {
			flash.message = "Sehr sportlich, Herr Kollege... Ihr Versuch zu Bescheissen wurde protokolliert und wird morgen in der Kaffeekueche ausgehaengt..."
			betInstance.discard()
			redirect(controller:'home', action:'index')
		} else {
	
	        betInstance.save flush:true
	
	        request.withFormat {
	            form {
	                flash.message = message(code: 'default.updated.message', args: [message(code: 'Bet.label', default: 'Bet'), betInstance.id])
	                redirect betInstance
	            }
	            '*'{ respond betInstance, [status: OK] }
	        }
		}
    }

    @Transactional
    def delete(Bet betInstance) {

        if (betInstance == null) {
            notFound()
            return
        }
		
		if (betInstance.user != springSecurityService.currentUser) {
			notYours()
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
	
	protected void notYours() {
		flash.message = "Wette deinen eigenen Kram. :-P"
		redirect action: "list", method: "GET"
	}
	
	protected void alreadyBet() {
		flash.message = "Es wurde bereits ein Tipp fuer dieses Spiel abgegeben!"
		redirect action: "list", method: "GET"
	}
	
	protected boolean cheating(Bet betInstance) {
		def game = betInstance.game
		def now = new Date()
		if (game.playAt <= now) {
			log.error "${betInstance.user} auf die Finger hauen! (${now}) for game ${game}"
			return true
		} else {
			return false
		}
	}
}
