package de.betgame

import de.betgame.sec.User;

class HomeController {

	def openLigaDBService
	def springSecurityService
	
    def index = { 
		//openLigaDBService.fetchTeamsAndGamesAndLocations()
		def today = (new Date()).clearTime()
		def nextGames = Game.findAllByPlayAtGreaterThan(today, [sort:'playAt', max:3])
		def user = springSecurityService.currentUser
		def myBets
		if (user) {
			myBets = Bet.findAllByUserAndGameInList(user, nextGames)
			myBets = myBets.groupBy { it.game }
		}
		
		def countBets = Bet.count()
		def betters = Bet.executeQuery("select distinct user from Bet b").size()
		def userCount = User.count()
		
		[nextGames:nextGames, myBets:myBets, countBets: countBets, userCount:userCount, betters:betters]
	}
}
