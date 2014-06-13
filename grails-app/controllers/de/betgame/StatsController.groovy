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
		
		println posMap
		
		[punkte : punkte, posMap : posMap]
	}
}
