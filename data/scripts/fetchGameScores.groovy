import de.betgame.*

import grails.converters.JSON
import grails.transaction.Transactional
import groovy.sql.Sql
import groovyx.net.http.FromServer
import groovyx.net.http.HttpBuilder

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
    
    def leagueShortcut = "wm_2018"
    def leagueSeason = "2018"
    //def leagueShortcut = "bl1"
    //def leagueSeason = "2016"
    
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
        if (game.matchIsFinished) {
            game.finalScore1 = (game.finalScore1 == null || game.finalScore1 < wsScore.PointsTeam1 ? wsScore.PointsTeam1 : game.finalScore1)
            game.finalScore2 = (game.finalScore2 == null || game.finalScore2 < wsScore.PointsTeam2 ? wsScore.PointsTeam2 : game.finalScore2)
        }
    }
    
    /// RUN FORREST, RUN! ======================================================================================
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
        
        Game game = Game.get(matchData.MatchID)
        
        if (game) {
            game.matchIsFinished = Boolean.parseBoolean(matchData.MathIsFinished)
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
                        ownGoal: Boolean.parseBoolean(g.IsOwnGoal),
                        penalty: Boolean.parseBoolean(g.IsPenalty),
                        overtime: Boolean.parseBoolean(g.IsOvertime)
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