package de.betgame

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional;

@Secured(['ROLE_EMPLOYEE_191', 'ROLE_STUDENTASSISTENT_191'])
@Transactional
class StatsController {

	def statsService
	def springSecurityService
	
	def index() { 
		redirect(action:'ranking')
	}
	
	def ranking() {
		def model = calcRanking(3, 2, 1)
		return model
	}
	
	def kicktippRanking() {
		def model = calcRanking(3, 2, 2)
		render(view: 'altranking', model:model)
	}
	
	def kickerRanking() {
		def model = calcRanking(3, 1, 1)
		render(view: 'altranking', model:model)
	}
	
	def rawRanking() {
		def model = calcRanking(1, 1, 1)
		render(view: 'altranking', model:model)
	}
	
	private calcRanking(ergebnisPunkte, tordiffPunkte, tendenzPunkte) {
		def result = statsService.getRanking(ergebnisPunkte, tordiffPunkte, tendenzPunkte)
		def punkte = result.sort { it.givenname }.sort { -it.punkte }
		
		def positions = punkte.groupBy { it.punkte }.sort { -it.key }
		def posMap = [:]
		def currentOffset = 1
		
		positions.each { points, players ->
			posMap[points] = currentOffset
			currentOffset += players.size()
		}
		
		// get the difference to yesterdays ranking
		def yResult = statsService.getYesterdaysRanking(ergebnisPunkte, tordiffPunkte, tendenzPunkte)
		def yPunkte = yResult.sort { it.givenname }.sort { -it.punkte }
		def yPositions = yPunkte.groupBy { it.punkte }.sort { -it.key }
		def yPosMap = [:]
		
		currentOffset = 1
		
		yPositions.each { points, players ->
			yPosMap[points] = currentOffset
			currentOffset += players.size()
		}
		
		def posChangeMap = [:]
		punkte.each { p ->
			def yUsersPunkte = yPunkte.find { it.user_id == p.user_id }?.punkte
			//println "${p.user_id} (${p.display}) ${p.punkte}, hatte ${yUsersPunkte}"
			if (yUsersPunkte && posMap[(p.punkte)] && yPosMap[yUsersPunkte]) {
				posChangeMap[(p.user_id)] = posMap[(p.punkte)] - yPosMap[yUsersPunkte]
			} else {
				posChangeMap[(p.user_id)] = null
			}
		}
		
		// count right scores, right tendencies, right game endings per user
		def finishedBets = Bet.executeQuery("from Bet b where b.game.playAt < :now", [now: new Date()]).groupBy { it.user.id }
		def betStats = [:]
		
		punkte.each { p ->
			def uid = p.user_id
			betStats[uid] = [:]
			finishedBets[uid].each { bet ->
				def score = bet.getScore()
				if (score == ergebnisPunkte) { betStats[uid]['E'] = (betStats[uid]['E'] ?: 0) + 1 }
				if (score == tordiffPunkte)  { betStats[uid]['T'] = (betStats[uid]['T'] ?: 0) + 1 }
				if (score == tendenzPunkte)  { betStats[uid]['S'] = (betStats[uid]['S'] ?: 0) + 1 }
			}
			
			betStats[uid]['percentage'] = Math.round(100.0 * (betStats[uid].values().sum()?.toDouble() ?: 0) / finishedBets[uid].size())
			betStats[uid]['betCount'] = finishedBets[uid].size()
		}
		
		def wertung = ['E': ergebnisPunkte, 'T': tordiffPunkte, 'S': tendenzPunkte]
		
		[punkte : punkte, posMap : posMap, betStats : betStats, posChangeMap : posChangeMap, wertung: wertung, curUser: springSecurityService.currentUser]
	}
	
	def luckers() {
		def result = statsService.getLuckers()
		def luckyShots = result.sort { it.givenname }.sort { -it.anz }
		
		def nameMap = NameUtil.buildNameMap(luckyShots)
		
		[luckyShots : luckyShots, nameMap: nameMap, curUser: springSecurityService.currentUser]
	}
	
	def scores() {
		def result = statsService.getScores()
		def users = Bet.executeQuery("select distinct user from Bet b")
		
		def userScores = [:]
		result.each { game, userMap ->
			userMap.each { user, bets ->
				bets.each { bet ->
					userScores[user] = (userScores[user] ?: 0) + bet.getScore()
				}
			}
		}
		
		users.sort { -1 * (userScores[(it.id)] ?: 0) }
		
		[users:users, result:result, userScores : userScores, curUser: springSecurityService.currentUser]
	}
}
