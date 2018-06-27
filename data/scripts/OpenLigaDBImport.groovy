import de.betgame.*

import grails.converters.JSON
import grails.transaction.Transactional
import groovy.sql.Sql
import groovyx.net.http.FromServer
import groovyx.net.http.HttpBuilder
import org.springframework.beans.factory.InitializingBean

import java.nio.charset.StandardCharsets
import groovyx.net.http.HttpBuilder

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
    def invokeWebserviceMethod = { String methodName, List extraParams = null ->
        httpBuilder.get {
            request.uri.path = "/api/$methodName/$leagueShortcut/$leagueSeason/${extraParams?.join('/') ?: ''}"
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

    def findCodeForCountryName = { String name ->
        return sql.rows("""select code from "countrynames" cn where cn.name = $name limit 1""")[0]?.code
    }

    /// METHODS ================================================================================================
    /**
     * Example:
     *
     * "Location": {
     *     "LocationCity": "Moskau",
     *     "LocationID": 367,
     *     "LocationStadium": "Luzhniki Stadion"
     * },
     */
    def createOrUpdateLocations = { matchData ->
        def locations = matchData*.Location.unique()

        locations.findAll { it.LocationCity != null }.each { loc ->
            Location l
            l = Location.findByFkid(loc.LocationID)
            if (!l) {
                l = new Location()
                l.id = loc.LocationID
                l.fkid = loc.LocationID
            }
            l.city = loc.LocationCity
            l.stadium = loc.LocationStadium

            l.save(failOnError:true)
        }
    }

    /**
     * Example:
     *
     * "Team1": {
     *    "ShortName": "",
     *    "TeamIconUrl": "http://www.dfs-wappen.de/media/land/wappen_fl/dfs_fl_russland.gif",
     *    "TeamId": 2926,
     *    "TeamName": "Russland"
     * },
     */
    def createOrUpdateTeam = { Map teamData ->
        Team t = Team.findByFkid(teamData.TeamId)
        if (!t) {
            t = new Team()
            t.id = teamData.TeamId
            t.fkid = teamData.TeamId
        }
        t.name = teamData.TeamName
        t.iconUrl = teamData.TeamIconUrl
        t.code = findCodeForCountryName(teamData.TeamName)

        t.save(failOnError : true, flush: true)
    }

    /**
     * Example: see big one above
     */
    def createOrUpdateGame = { Map match ->
        Location l = Location.findByFkid(match.Location.LocationID)
        Team t1 = Team.findByFkid(match.Team1.TeamId)
        Team t2 = Team.findByFkid(match.Team2.TeamId)

        Game g = Game.findByFkid(match.MatchID)
        if (!g) {
            log.error "Could not find Game with fkid ${match.MatchID}\n${match}"
            return
            g = new Game()
            g.id = match.MatchID
            g.fkid = match.MatchID
        }

        g.team1 = t1
        g.team2 = t2
        g.location = l
        g.playAt = Date.parse("yyyy-MM-dd'T'HH:mm:ss", match.MatchDateTime)
        g.playAtUTC = Date.parse("yyyy-MM-dd'T'HH:mm:ss", match.MatchDateTimeUTC)
        g.matchIsFinished = match.MatchIsFinished
        g.numberOfViewers = match.NumberOfViewers ?: null
        g.phase = match.Group.GroupName?.startsWith("Gruppe ") ? 'Vorrunde' : match.Group.GroupName.replaceAll(" ", "")

        g.save(failOnError: true)
    }

    def postProcessingTeams = {
        // setting the actual group
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

    def fetchGroups = {
        def groups = invokeWebserviceMethod("getavailablegroups")
        log.warn "${groups}"
        return groups
    }

    def fetchTeamsAndGamesAndLocations = {
        def groups = fetchGroups()
        groups.each { grp ->
            if (grp.GroupOrderID) {
                log.warn "Processing ${grp.GroupOrderID}"
                def matchData = invokeWebserviceMethod("getmatchdata", [grp.GroupOrderID])
                log.warn "$matchData"

                createOrUpdateLocations(matchData)

                matchData.findAll { it.MatchID > 0 }.each { match ->
                    log.info "Processing Match: $match"

                    createOrUpdateTeam(match.Team1)
                    createOrUpdateTeam(match.Team2)
                    createOrUpdateGame(match)
                }
            } else {
                log.error "Skipping grp"
            }
        }

        //postProcessingTeams()
        //postProcessingGames()
    }

    def deleteAllData = {
        log.warn "DELETING ALL DATA!"
        sql.execute("delete from bet")
        sql.execute("delete from game")
        sql.execute("delete from location")
        sql.execute("delete from team")
        log.warn "Done."
    }

    /// SCRIPT STUFF ============================================================================================
    //deleteAllData()
    fetchTeamsAndGamesAndLocations()

}