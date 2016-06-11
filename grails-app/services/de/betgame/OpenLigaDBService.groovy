package de.betgame

import grails.converters.JSON;
import grails.converters.XML;
import grails.transaction.Transactional
import groovy.sql.Sql;

import de.msiggi.sportsdata.webservices.SportsdataSoap;

/**
 * OpenLigaDBService
 * A service class encapsulates the core business logic of a Grails application
 */
@Transactional
class OpenLigaDBService {
	
	def dataSource_betgame
	
	SportsdataSoap openligaDBServiceClient
	
	def leagueShortcut = "em2016"
	def leagueSeason = "2016"

	def test() {
		def res = openligaDBServiceClient.getAvailLeagues()
		log.warn res
		//def matchData = JSON.parse(openligaDBServiceClient.GetMatchdataByGroupLeagueSaisonJSON(1, leagueShortcut, leagueSaison))
	}
	
	def fetchGroups() {
		def groups = openligaDBServiceClient.getAvailGroups(leagueShortcut, leagueSeason)
		println groups.group.each { g ->
			println g.properties
		}
	}
	
	def fetchTeamsAndGamesAndLocationsDryRun() {
		def matchData = JSON.parse(openligaDBServiceClient.getMatchdataByGroupLeagueSaisonJSON(2, leagueShortcut, leagueSeason))
		//matchData.findAll { it.groupName != 'Vorrunde' }.each { m ->
		matchData.each { m ->
			println m
		}
		println matchData.size()
	}
	
	def fetchTeamsAndGamesAndLocationsForKnockouts() {
		(2..6).each { groupOrderID ->
			def matchData = JSON.parse(openligaDBServiceClient.getMatchdataByGroupLeagueSaisonJSON(groupOrderID, leagueShortcut, leagueSeason))
			
			log.info matchData
			
			matchData.each { match ->
				if (match.matchID != -1) {
					log.info "Processing Match: $match"
					createOrUpdateGame(match)
				}
			}
		}
	}
	
	def fetchTeamsAndGamesAndLocations() {
		def groups = openligaDBServiceClient.getAvailGroups(leagueShortcut, leagueSeason)
		groups.group.each { grp ->
			if (grp.groupOrderID) {
				log.warn "Processing ${grp.groupOrderID}"
				def matchData = JSON.parse(openligaDBServiceClient.getMatchdataByGroupLeagueSaisonJSON(grp.groupOrderID, leagueShortcut, leagueSeason))
				
				createOrUpdateLocations(matchData)
				
				matchData.findAll { it.matchID > 0 }.each { match ->
					log.info "Processing Match: $match"
					
					createOrUpdateTeam(match.idTeam1, match.nameTeam1, match.iconUrlTeam1)
					createOrUpdateTeam(match.idTeam2, match.nameTeam2, match.iconUrlTeam2)
					createOrUpdateGame(match)
				}
			} else {
				log.error "Skipping grp"
			}
		}
		postProcessingTeams()
		//postProcessingGames()
	}
	
	def createOrUpdateLocations(matchData) {
		def locations = matchData*.location.unique()
		
		locations.findAll { it.locationCity != null }.each { loc ->
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
		g.playAt = match.matchDateTime
		g.playAtUTC = match.matchDateTimeUTC
		g.matchIsFinished = match.matchIsFinished
		g.numberOfViewers = match.NumberOfViewers ?: null
		g.groupName = match.groupName ? match.groupName.replace("Gruppe ", "") : null
		g.phase = match.groupName?.startsWith("Gruppe ") ? 'Vorrunde' : match.groupName.replaceAll(" ", "")
		
		g.save(failOnError: true)
	}
	
	def updateGameScore(gameID, force = false) {
		Game game = Game.get(gameID)
		log.debug "fetching game data for ${gameID}..."
		if (game) {
			def matchData = openligaDBServiceClient.getMatchByMatchID(gameID.toInteger())
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
				
				def endErgeb
				// this seems to be the better way to fetch latest result
				if (matchData?.matchResults?.matchResult?.size() > 0) {
					// nach Nachspielzeit
					endErgeb = matchData.matchResults.matchResult.find { it.resultTypeId == 3 }
					if (!endErgeb) {
						// ordered last result 
						endErgeb = matchData.matchResults.matchResult.findAll { it.resultTypeId <= 3 }.sort { it.resultOrderID }[-1] 
					}
				}
				log.info "endErgeb: ${endErgeb?.properties}"
				
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
				} else {
					log.warn "No valid results found! Raw response:"
					matchData?.matchResults?.matchResult.each {
						log.warn it.properties
					}
				}
			} else {
				log.error "Could not fetch all match data: ${matchData.properties}"
			}
		}
	}
	
	def postProcessingTeams() {
		Sql sql = new Sql(dataSource_betgame)
		sql.execute("""update team t set code = (select code from "countryNames" cn where t.name = cn.name limit 1) where code is null""")
	}
	
}

