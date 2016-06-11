package de.betgame

import de.betgame.sec.User;
import de.msiggi.sportsdata.webservices.SportsdataSoap;

class HomeController {

	def openLigaDBService
	def springSecurityService
	
    def index = { 
		//openLigaDBService.fetchTeamsAndGamesAndLocations()
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
	
	def fixdisplay() {
		def users = User.executeQuery("from User u where u in (select distinct b.user from Bet b)")
		// reset display names
		users.each { u -> 
			u.display = u.givenname
			u.save(flush:true)
		}
		
		// fetch non unique ones
		def multidisplay = users.countBy { it.display }.findAll { it.value > 1 }
		def lettersFromLastName = 0
		
		// add surname letters until everyone has a unique display name
		while(multidisplay) {
			lettersFromLastName++
			multidisplay.each { dis, num ->
				users.findAll { it.display == dis }.each { u ->
					u.display = "${u.givenname}${u.surname[0..(lettersFromLastName-1)]}"
					u.save()
				}
			}
			multidisplay = users.countBy { it.display }.findAll { it.value > 1 }
		}
		render "${users.size()}"
	}
}
