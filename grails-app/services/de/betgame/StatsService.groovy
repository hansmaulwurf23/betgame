package de.betgame

import grails.transaction.Transactional;
import groovy.sql.Sql;

@Transactional
class StatsService {

	def dataSource_betgame
	
	def getRanking() {
		return getRanking(3, 2, 1)
	}
	
    def getRanking(ergebnisPunkte, tordiffPunkte, tendenzPunkte) {
		Sql sql = new Sql(dataSource_betgame)
		def rows = sql.rows("""

			select punkte, username, givenname, surname, display, user_id
			from (
				select sum(punkte) as punkte, user_id
				from (
					select *,
					CASE
						WHEN g.score1 = b.score1 AND g.score2 = b.score2 THEN ${ergebnisPunkte}
						WHEN g.score1 - g.score2 = b.score1 - b.score2 THEN ${tordiffPunkte}
						WHEN sign(g.score1 - g.score2) = sign(b.score1 - b.score2) THEN ${tendenzPunkte}
						ELSE 0 
						END as punkte
					from game g join bet b using (game_id) 
					where play_at < now() and g.score1 is not null and g.score2 is not null
				) as punkte
				group by user_id
			) as punktesummen join sec."users" u  on punktesummen.user_id = u.id

		""")
		return rows
    }
	
	def getYesterdaysRanking() {
		return getYesterdaysRanking(3, 2, 1)
	}
	
	def getYesterdaysRanking(ergebnisPunkte, tordiffPunkte, tendenzPunkte) {
		Sql sql = new Sql(dataSource_betgame)
		
		def lastGameDate = sql.rows("select date_trunc('day', play_at) as lastDatum from game where play_at < now() order by play_at desc limit 1")[0]
		
		if (!lastGameDate) {
			lastGameDate = new Date() - 1
		}
		
		def rows = sql.rows("""

			select punkte, username, givenname, surname, display, user_id
			from (
				select sum(punkte) as punkte, user_id
				from (
					select *,
					CASE
						WHEN g.score1 = b.score1 AND g.score2 = b.score2 THEN ${ergebnisPunkte}
						WHEN g.score1 - g.score2 = b.score1 - b.score2 THEN ${tordiffPunkte}
						WHEN sign(g.score1 - g.score2) = sign(b.score1 - b.score2) THEN ${tendenzPunkte}
						ELSE 0 
						END as punkte
					from game g join bet b using (game_id) 
					where play_at < ${new java.sql.Date(lastGameDate.time)} and g.score1 is not null and g.score2 is not null
				) as punkte
				group by user_id
			) as punktesummen join sec."users" u  on punktesummen.user_id = u.id

		""")
		return rows
	}
	
	def getLuckers() {
		Sql sql = new Sql(dataSource_betgame)
		def rows = sql.rows("""

			select anz, username, givenname, surname, display, user_id
				from (
					select user_id, count(*) as anz
					from game g join bet b using (game_id) 
					where play_at < now() and g.score1 is not null and g.score2 is not null and g.score1 = b.score1 and g.score2 = b.score2
					group by user_id
				) as luckers join sec."users" u on luckers.user_id = u.id

		""")
		return rows
	}
	
	def getScores() {
		def allFinishedGames = Game.findAllByMatchIsFinished(true)
		def allBets = Bet.findAllByGameInList(allFinishedGames, [sort:'game.playAt']).groupBy([{ it.game.id },{ it.user.id }])
		return allBets
	}
	
}
