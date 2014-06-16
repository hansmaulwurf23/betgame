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
		
		def nameMap = NameUtil.buildNameMap(punkte) 
		
		[punkte : punkte, posMap : posMap, nameMap: nameMap]
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
		def nameMap = NameUtil.buildNameMap(users).sort { it.value }
		
		[nameMap:nameMap, result:result]
	}
}
