package de.betgame

import grails.converters.JSON;
import grails.transaction.Transactional
import groovy.sql.Sql;

import org.grails.plugins.wsclient.service.WebService

/**
 * OpenLigaDBService
 * A service class encapsulates the core business logic of a Grails application
 */
@Transactional
class OpenLigaDBService {
	
	WebService webService
	def dataSource_betgame
	
	def leagueShortcut = "WM-2014"
	def leagueSaison = "2014"

    def getProxy() {
		def proxy = webService.getClient("http://www.OpenLigaDB.de/Webservices/Sportsdata.asmx?WSDL")
		if (proxy) {
			return proxy
		} else {
			throw new RuntimeException("Failed to initialize WebService")
		}
    }
	
	def test() {
		println getProxy().GetAvailGroups(leagueShortcut, leagueSaison).group.each {
			println it.properties
			println it
		}
		def matchData = JSON.parse(getProxy().GetMatchdataByGroupLeagueSaisonJSON(1, "WM-2014", "2014"))
		
		matchData.each { m ->
			println m
		}
	}
	
	def fetchTeamsAndGamesAndLocations() {
		def matchData = JSON.parse(getProxy().GetMatchdataByGroupLeagueSaisonJSON(1, leagueShortcut, leagueSaison))
		
		createOrUpdateLocations(matchData)
		
		matchData.each { match ->
			log.info "Processing Match: $match"
			
			createOrUpdateTeam(match.idTeam1, match.nameTeam1, match.iconUrlTeam1)
			createOrUpdateTeam(match.idTeam2, match.nameTeam2, match.iconUrlTeam2)
			createOrUpdateGame(match)
		}
		
		postProcessingTeams()
		postProcessingGames()
	}
	
	def createOrUpdateLocations(matchData) {
		def locations = matchData*.location.unique()
		
		locations.each { loc ->
			log.info "Processing Location: $loc"
			Location l
			l = Location.get(loc.locationID)
			if (!l) {
				l = new Location()
				l.id = loc.locationID
			}
			l.city = loc.locationCity
			l.stadium = loc.locationStadium
			
			l.save(failOnError:true)
		}
	}
	
	def createOrUpdateTeam(Integer id, String name, String iconUrl) {
		Team t = Team.get(id)
		if (!t) {
			t = new Team()
			t.id = id
		}
		t.name = name
		t.iconUrl = iconUrl
		t.save(failOnError : true)
	}
	
	def createOrUpdateGame(match) {
		Location l = Location.get(match.location.locationID)
		Team t1 = Team.get(match.idTeam1)
		Team t2 = Team.get(match.idTeam2)
		
		Game g = Game.get(match.matchID)
		if (!g) {
			g = new Game()
			g.id = match.matchID
		}
		
		g.team1 = t1
		g.team2 = t2
		g.location = l
		g.score1 = match.pointsTeam1 < 0 ? null : match.pointsTeam1 
		g.score2 = match.pointsTeam2 < 0 ? null : match.pointsTeam2
		g.lastUpdated = match.lastUpdate
		g.playAt = match.matchDateTime
		g.playAtUTC = match.matchDateTimeUTC
		g.matchIsFinished = match.matchIsFinished
		g.numberOfViewers = match.NumberOfViewers ?: null
		
		g.save(failOnError: true)
	}
	
	def updateGameScore(gameID, force = false) {
		Game game = Game.get(gameID)
		log.debug "fetching game data for ${gameID}..."
		if (game) {
			def matchData = getProxy().GetMatchByMatchID(gameID)
			if (matchData.matchID != -1) {
				def goals = matchData.goals?.goal
				if (goals) {
					def infos = []
					goals.each { g->
						infos << [score1: g.goalScoreTeam1, score2: g.goalScoreTeam2, getter: g.goalGetterName, minute: g.goalMatchMinute, ownGoal: g.goalOwnGoal, penalty: g.goalPenalty, overtime: g.goalOvertime]
					}
					
					game.goals = (infos as JSON)
					game.save(flush:true)
				}
				
				def endErgeb = matchData?.matchResults?.matchResult?.find { it.resultName == 'Endergebnis' }
				log.info "Endergebnis: $endErgeb"
				if (!endErgeb) {
					log.info "No Endergebnis... trying Halbzeitergebnis"
					endErgeb = matchData?.matchResults?.matchResult?.find { it.resultName == 'Halbzeitergebnis' }
				}
				if (endErgeb) {
					int s1 = endErgeb.pointsTeam1?.toInteger()
					int s2 = endErgeb.pointsTeam2?.toInteger()
					log.debug "fetched game data for ${gameID}: $s1 : $s2 (finished = ${matchData.matchIsFinished})"
					if (force || game.score1 != s1 || game.score2 != s2 || game.numberOfViewers != matchData.numberOfViewers || game.matchIsFinished != matchData.matchIsFinished) {
						log.warn "UPDATING GAME SCORE FOR GAME ${gameID}: $s1 - $s2"
						game.score1 = s1 
						game.score2 = s2
						game.numberOfViewers = matchData.numberOfViewers
						game.matchIsFinished = matchData.matchIsFinished
						game.save(flush:true, failOnError:true)
					}
				}
			} else {
				log.error "Could not fetch all match data: ${matchData.properties}"
			}
		}
	}
	
	// fuck sqlite. -> removed opensportdb code
	/*
	def postProcessingTeams() {
		Sql sql = new Sql(dataSource_betgame)
		def teams = Team.findAllByNetIsNull()
		teams.each { t ->
			def englishName = sql.rows("""
				select name from "countryNames" where locale != 'de_DE' and code = (select code from "countryNames" where locale = 'de_DE' and name = ${t.name})
			""")[0]

			if (englishName) {
				def sprtDBTeam = Teams.findByTitle(englishName.name)
				if (sprtDBTeam) {
					t.code = sprtDBTeam.code
					t.net = sprtDBTeam.country.net
					t.save(failOnError:true)
					log.warn "Found OpenSportDB Team: $sprtDBTeam and updated Team: $t"
				} else {
					log.warn "No OpenSportDB Team found for english name $englishName"
				}
			} else {
				log.warn "No english name found for ${t.name}"
			}
		}
	}
	
	def postProcessingGames() {
		def games = Game.findAllByGroupNameIsNull()
		games.each { g ->
			Teams t1 = Teams.findByCode(g.team1.code)
			Teams t2 = Teams.findByCode(g.team2.code)
			if (t1 && t2) {
				def sprtDBGames = Games.findAllByTeam1AndTeam2(t1, t2)
				def groupNames = sprtDBGames*.group*.title
				if (groupNames && groupNames.size() == 1) {
					log.warn "Found Group info for game $g : ${groupNames[0]}"
					g.groupName = groupNames[0][-1]
					g.save(failOnError: true)
				} else {
					log.warn "Found nothing/too much: $groupNames for game ${g.properties}"
				}
			}
		}
	}
	*/
}

