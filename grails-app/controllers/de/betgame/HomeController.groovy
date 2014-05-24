package de.betgame

class HomeController {

	def openLigaDBService
	def springSecurityService
	
    def index = { 
		//openLigaDBService.fetchTeamsAndGamesAndLocations()
		def nextGame = Game.findByPlayAtGreaterThan(new Date(), [max:1, sort:'playAt'])
		def user = springSecurityService.currentUser
		def myBet
		if (user) {
			myBet = Bet.findByUserAndGame(user, gameInstance)
		}
		[nextGame:nextGame, myBet:myBet]
	}
}
