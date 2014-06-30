package de.betgame

class FetchKORoundGamesJob {
	
	static triggers = {
		cron name: 'FetchKORoundGamesJobTrigger', cronExpression: "0 0 */2 * * ?"
	}
	
	def group = "main"
	def concurrent = false
	def sessionRequired = true
	
	def openLigaDBService
	
	def execute(context) {
		long start = System.currentTimeMillis()

		Game.withTransaction {
			openLigaDBService.fetchTeamsAndGamesAndLocationsForKnockouts()
		}
		
		log.info "FetchKORoundGamesJob took ${System.currentTimeMillis() - start}ms"
	}
}
