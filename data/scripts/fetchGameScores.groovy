import de.betgame.*

import grails.converters.JSON
import grails.transaction.Transactional
import groovy.sql.Sql
import groovyx.net.http.FromServer
import groovyx.net.http.HttpBuilder
import org.springframework.beans.factory.InitializingBean

import java.nio.charset.StandardCharsets
import groovyx.net.http.HttpBuilder
import groovy.json.*

/**
 * Updates game scores. Either with all games or one specific game if gameid is given.
 * Verbose is only for logging reasons.
 */
process {

    def gameID = ctx.callParams.gameid
    boolean verbose = (ctx.callParams.verbose == true)

    //log.warn "Running with gameID $gameID and verbose $verbose"

    /// CONFIGURATION ===========================================================================================
    def dataSource = grailsApplication.mainContext.getBean('dataSource')
    Sql sql = new Sql(dataSource)

    HttpBuilder httpBuilder
    String openLigaDBURL = "https://www.openligadb.de/api/"

    //def leagueShortcut = "wm_2018"
    //def leagueSeason = "2018"
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

    def updateScores = { game, wsScore ->
        if (wsScore == null || game == null) {
            return
        }
        // update only if not preset and new score is higher (since some users can manually update)
        game.score1 = (game.score1 == null || game.score1 < wsScore.PointsTeam1 ? wsScore.PointsTeam1 : game.score1)
        game.score2 = (game.score2 == null || game.score2 < wsScore.PointsTeam2 ? wsScore.PointsTeam2 : game.score2)

        game.finalScore1 = game.score1
        game.finalScore2 = game.score2
    }

    /// RUN FORREST, RUN! ======================================================================================
    Game.withNewTransaction {
        def matchDatas = []

        if (gameID) {
            matchDatas << invokeWebserviceMethod("getmatchdata", [gameID], true)
        } else {
            matchDatas = invokeWebserviceMethod("getmatchdata")
        }

        matchDatas.each { matchData ->
            if (verbose) {
                log.info "${new JsonBuilder(matchData).toPrettyString() }"
            }

            Game game = Game.findByFkid(matchData.MatchID)

            if (game) {
                game.matchIsFinished = game.matchIsFinished ?: matchData.MatchIsFinished
                game.numberOfViewers = matchData.NumberOfViewers
                def results = matchData.MatchResults
                if (results) {
                    def maxResultOrderID = results*.ResultOrderID.max()
                    def latestResult = results.find { it.ResultOrderID == maxResultOrderID }
                    updateScores(game, latestResult)
                } else {
                    if (game.playAt < new Date() || verbose) {
                        log.warn "No Results for Game ${matchData.MatchID}"
                    }
                }

                def goals = matchData.Goals
                if (goals) {
                    def infos = []
                    goals.each { g ->
                        infos << [
                                score1: g.ScoreTeam1,
                                score2: g.ScoreTeam2,
                                getter: g.GoalGetterName,
                                minute: g.MatchMinute,
                                ownGoal: Boolean.parseBoolean(g.IsOwnGoal.toString()),
                                penalty: Boolean.parseBoolean(g.IsPenalty.toString()),
                                overtime: Boolean.parseBoolean(g.IsOvertime.toString())
                        ]
                    }
                    game.goals = (infos as JSON)
                } else {
                    if (game.playAt < new Date() || verbose) {
                        log.info "No Goals info found for ${matchData.MatchID}"
                    }
                }

                game.save(flush:true)
            } else {
                log.error "Game with ID ${matchData.MatchID} not found!"
            }
        }
    }

}