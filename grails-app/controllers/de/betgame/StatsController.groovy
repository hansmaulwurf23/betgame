package de.betgame

class StatsController {

	def statsService
	
	def index() { 
		redirect(action:'ranking')
	}
	
	def ranking() {
		def result = statsService.getRanking()
		def punkte = result.sort { it.givenname }.sort { -it.punkte }
		
		def positions = punkte*.punkte.unique()
		def posMap = [:]
		
		positions.eachWithIndex { p, i ->
			posMap[p] = (i+1)
		}
		
		[punkte : punkte, posMap : posMap]
	}
}
