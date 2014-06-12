package de.betgame

import de.betgame.sec.User;

class HomeController {

	def openLigaDBService
	def springSecurityService
	
    def index = { 
		//openLigaDBService.fetchTeamsAndGamesAndLocations()
		def nextGame = Game.findByPlayAtGreaterThan(new Date(), [max:1, sort:'playAt'])
		def user = springSecurityService.currentUser
		def myBet
		if (user) {
			myBet = Bet.findByUserAndGame(user, nextGame)
		}
		
		def countBets = Bet.count()
		def betters = Bet.executeQuery("select distinct user from Bet b").size()
		def userCount = User.count()
		
		[nextGame:nextGame, myBet:myBet, countBets: countBets, userCount:userCount, betters:betters]
	}
}
