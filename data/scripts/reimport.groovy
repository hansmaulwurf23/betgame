import de.betgame.*

import grails.converters.JSON
import groovy.sql.Sql
import groovyx.net.http.FromServer
import java.nio.charset.StandardCharsets
import groovyx.net.http.HttpBuilder
import groovy.json.*

/**
 * Updates game scores. Either with all games or one specific game if gameid is given.
 * Verbose is only for logging reasons.
 */
process {

    /// CONFIGURATION ===========================================================================================
    def dataSource = grailsApplication.mainContext.getBean('dataSource')
    Sql sql = new Sql(dataSource)

    HttpBuilder httpBuilder
    String openLigaDBURL = "https://www.openligadb.de/api/"

    def leagueShortcut = "fifa18"
    def leagueSeason = "2018"

    httpBuilder = HttpBuilder.configure {
        request.uri = openLigaDBURL
        request.accept = 'application/json'
    }

    /// HELPERS =================================================================================================
    def invokeWebserviceMethod = { String methodName, List extraParams = null, Boolean noDefaultParams = null ->
        def path

        if (noDefaultParams) {
            path = "/api/$methodName/${extraParams?.join('/') ?: ''}"
        } else {
            path = "/api/$methodName/$leagueShortcut/$leagueSeason/${extraParams?.join('/') ?: ''}"
        }

        httpBuilder.get {

            request.uri.path = path
            request.charset = StandardCharsets.UTF_8

            response.success { FromServer fs, responseJson ->
                if (fs.headers.find { it.key == 'Content-Type' }?.getValue()?.contains('json')) {
                    return responseJson
                } else {
                    log.error "No JSON was returned in method ${request.uri.getPath()} Status: ${fs.statusCode}"
                    return null
                }
            }

            response.failure { x, rawResponseBody ->
                log.error "Post-Request failed! ${rawResponseBody}, HTTP Status Code: ${rawResponseBody.statusCode}"
            }
        }
    }

    /// RUN FORREST, RUN! ======================================================================================
    Game.withNewTransaction {
        def matchDatas = invokeWebserviceMethod("getmatchdata")


        // TEAMS
        def teams = (matchDatas*.Team1 + matchDatas*.Team2).unique()
        teams.each { t ->
            Team team = Team.findByName(t.TeamName)
            if (team) {
                log.warn "${t.TeamName}"
                team.fkid = t.TeamId
                team.save(flush:true)
            } else {
                log.warn "Team ${t.TeamName} could not be found!\n${t}"
            }
        }

        // LOCATIONS
        def locations = matchDatas*.Location.unique()
        locations.each { l ->
            Location location = Location.findByCity(l.LocationCity)
            if (location) {
                location.fkid = l.LocationID
                location.save(flush:true)
            } else {
                log.warn "Location ${l} not found!"
            }
        }

        matchDatas.each { matchData ->
            def ht = Team.findByFkid(matchData.Team1.TeamId)
            def ft = Team.findByFkid(matchData.Team2.TeamId)
            // Careful since teams can meet again if tournament is in KO phase!
            if (ht && ft) {
                Game game = Game.findByTeam1AndTeam2(ht, ft)
                if (game) {
                    game.fkid = matchData.MatchID
                    game.save(flush:true)
                } else {
                    log.warn "Game not found! (${ft.name}:${ht.name})"
                }
            } else {
                log.warn "Teams not found! HT $ht FT $ft ($matchData)"
            }
        }

        def groups = [
                A: ['RU', 'SA', 'EG', 'UY'], B: ['PT', 'ES', 'MA', 'IR'], C: ['FR', 'AU', 'PE', 'DK'], D: ['AR', 'IS', 'HR', 'NG'],
                E: ['BR', 'CH', 'CR', 'RS'], F: ['DE', 'MX', 'SE', 'KR'], G: ['BE', 'PA', 'TN', 'EN'], H: ['PL', 'SN', 'CO', 'JP']
        ]

        groups.each { grpName, codes ->
            codes.each { code ->
                Team t = Team.findByCode(code)
                if (!t) return
                t.groupName = grpName
                t.save(flush:true)
            }
        }
    }

}