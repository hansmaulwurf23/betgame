package de.betgame

import grails.converters.JSON;
import grails.transaction.Transactional

import org.grails.plugins.wsclient.service.WebService

/**
 * OpenLigaDBService
 * A service class encapsulates the core business logic of a Grails application
 */
@Transactional
class OpenLigaDBService {
	
	WebService webService
	
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
}

