package de.betgame

import grails.plugins.springsecurity.Secured;

@Secured(['ROLE_EMPLOYEE_191'])
class StatsController {

	def statsService
	
	def index() { 
		redirect(action:'ranking')
	}
	
	def ranking() {
		def result = statsService.getRanking()
		def punkte = result.sort { it.givenname }.sort { -it.punkte }
		
		def positions = punkte.groupBy { it.punkte }.sort { -it.key }
		def posMap = [:]
		def currentOffset = 1
		
		positions.each { points, players ->
			posMap[points] = currentOffset
			currentOffset += players.size()
		}
		
		// get the difference to yesterdays ranking
		def yResult = statsService.getYesterdaysRanking()
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
				if (score == 3) { betStats[uid]['E'] = (betStats[uid]['E'] ?: 0) + 1 }
				if (score == 2) { betStats[uid]['T'] = (betStats[uid]['T'] ?: 0) + 1 }
				if (score == 1) { betStats[uid]['S'] = (betStats[uid]['S'] ?: 0) + 1 }
			}
		}
		
		[punkte : punkte, posMap : posMap, betStats : betStats, posChangeMap : posChangeMap]
	}
	
	def luckers() {
		def result = statsService.getLuckers()
		def luckyShots = result.sort { it.givenname }.sort { -it.anz }
		
		def nameMap = NameUtil.buildNameMap(luckyShots)
		
		[luckyShots : luckyShots, nameMap: nameMap]
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
		
		[users:users, result:result, userScores : userScores]
	}
}
