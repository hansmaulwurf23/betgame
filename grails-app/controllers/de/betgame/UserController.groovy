package de.betgame

import grails.plugin.springsecurity.annotation.Secured;
import de.betgame.sec.User
import grails.transaction.Transactional;

@Secured(['ROLE_EMPLOYEE_191', 'ROLE_STUDENTASSISTENT_191'])
@Transactional
class UserController {

	static pointNames = [0: 'Falsch', 1: 'Tendenz', 2:'Tordiff.', 3:'Ergebnis']
	static gameNames = [(-1): 'Heim', 0:'Remis', 1:'Gast']
	
	def statsService
	
	def show(User user) {
		def finishedGames = Game.findAllByPlayAtLessThan(new Date())
		def bets = Bet.findAllByUserAndGameInList(user, finishedGames)
		def allBets = Bet.findAllByGameInList(finishedGames)
		
		def scoreDistr = [:]
		bets.countBy { it.getScore() }.sort { -1 * it.key }.each { k,v ->
			scoreDistr[(pointNames[k])] = v
		}
		
		def history = []
		def avgHistory = []
		allBets = allBets.groupBy { it.game.playAt.clone().clearTime() }
		bets.groupBy { it.game.playAt.clone().clearTime() }.sort { it.key }.each { spielTag, tipps ->
			history << tipps*.getScore().sum()
			avgHistory << (allBets[spielTag]*.getScore().sum() / allBets[spielTag].size() * tipps.size())
		}
		def barData = [labels:['Punkte pro Spieltag', 'durchschn. Punkte des Spieltags'], data:[history, avgHistory]]
		
		def betPerc = finishedGames ? (bets.size().toDouble() / finishedGames.size() * 100) : 0.toDouble()
		
		[user: user, scoreDistr: scoreDistr, betPerc:betPerc, barData:barData, bets:bets.sort { it.game.playAt }]
	}	
}
