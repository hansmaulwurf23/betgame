package de.betgame

import de.betgame.sec.User;
import de.msiggi.sportsdata.webservices.SportsdataSoap;

class HomeController {

	def openLigaDBService
	def springSecurityService
	
    def index = { 
		//openLigaDBService.fetchTeamsAndGamesAndLocations()
		openLigaDBService.updateGameScore(37181)
		
		def today = (new Date()).clearTime()
		def nextGames = Game.findAllByMatchIsFinished(false, [sort:'playAt', max:4])
		def lastGames = Game.findAllByMatchIsFinished(true, [sort:'playAt', order:'desc', max:4])
		def user = springSecurityService.currentUser
		def myBets
		if (user) {
			myBets = Bet.findAllByUserAndGameInList(user, nextGames+lastGames)
			myBets = myBets.groupBy { it.game }
		}
		
		def countBets = Bet.count()
		def betters = Bet.executeQuery("select distinct user from Bet b").size()
		def userCount = User.count()
		
		[nextGames:nextGames, lastGames:lastGames, myBets:myBets, countBets: countBets, userCount:userCount, betters:betters]
	}
}
