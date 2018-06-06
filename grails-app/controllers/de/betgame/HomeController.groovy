package de.betgame

import de.betgame.sec.User;
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional;

@Transactional
class HomeController {
	
	def springSecurityService
	
	def index() {
		def today = (new Date()).clearTime()
		def nextGames = Game.findAllByMatchIsFinished(false, [sort: 'playAt', max: 4])
		def lastGames = Game.findAllByMatchIsFinished(true, [sort: 'playAt', order: 'desc', max: 4])
		def user = springSecurityService.currentUser
		def myBets
		if (user && (nextGames || lastGames)) {
			myBets = Bet.findAllByUserAndGameInList(user, nextGames + lastGames)
			myBets = myBets.groupBy { it.game }
		}
		
		def countBets = Bet.count()
		def betters = Bet.executeQuery("select distinct user from Bet b").size()
		def userCount = User.count()
		
		[nextGames: nextGames, lastGames: lastGames, myBets: myBets, countBets: countBets, userCount: userCount, betters: betters]
	}
	
	@Secured('isFullyAuthenticated()')
	def auth() {
		redirect(action: 'index')
	}
	
}