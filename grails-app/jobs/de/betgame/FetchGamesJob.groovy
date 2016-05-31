package de.betgame

class FetchGamesJob {
	
	static triggers = {
		cron name: 'FetchGamesJobTrigger', cronExpression: "0 0 */12 * * ?"
	}
	
	def group = "main"
	def concurrent = false
	def sessionRequired = true
	
	def openLigaDBService
	
	def execute(context) {
		long start = System.currentTimeMillis()

		Game.withTransaction {
			openLigaDBService.fetchTeamsAndGamesAndLocations()
		}
		
		log.info "FetchGamesJob took ${System.currentTimeMillis() - start}ms"
	}
}
