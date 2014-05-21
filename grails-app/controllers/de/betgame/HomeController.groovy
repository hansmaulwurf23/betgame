package de.betgame

class HomeController {

	def openLigaDBService
	
    def index = { 
		openLigaDBService.fetchTeamsAndGamesAndLocations()
	}
}
