package de.betgame

import grails.transaction.Transactional;
import groovy.sql.Sql;

@Transactional
class StatsService {

	def dataSource_betgame
	
    def getRanking() {
		Sql sql = new Sql(dataSource_betgame)
		def rows = sql.rows("""

			select punkte, username, givenname, surname
			from (
				select sum(punkte) as punkte, user_id
				from (
					select *,
					CASE
						WHEN g.score1 = b.score1 AND g.score2 = b.score2 THEN 3
						WHEN g.score1 - g.score2 = b.score1 - b.score2 THEN 2
						WHEN sign(g.score1 - g.score2) = sign(b.score1 - b.score2) THEN 1
						ELSE 0 
						END as punkte
					from game g join bet b using (game_id) 
					where play_at < now() and g.score1 is not null and g.score2 is not null
				) as punkte
				group by user_id
			) as punktesummen join "users" u  on punktesummen.user_id = u.id

		""")
		return rows
    }
	
	def getLuckers() {
		Sql sql = new Sql(dataSource_betgame)
		def rows = sql.rows("""

			select anz, username, givenname, surname
				from (
					select user_id, count(*) as anz
					from game g join bet b using (game_id) 
					where play_at < now() and g.score1 is not null and g.score2 is not null and g.score1 = b.score1 and g.score2 = b.score2
					group by user_id
				) as luckers join "users" u on luckers.user_id = u.id

		""")
		return rows
	}
	
	def getScore(game, bet) {
		def score = 0
		if (game.score1 != null && game.score2 != null) {
			if (game.score1 == bet.score1 && game.score2 == bet.score2) {
				score = 3
			} else if (game.score1-game.score2 == bet.score1-bet.score2) {
				score = 2
			// maybe we use Integer.signum() for this, too ;-)
			} else if (((game.score1-game.score2)<0)==((bet.score1-bet.score2)<0) && game.score1-game.score2 != 0) {
				score = 1
			}
		}
		return score
	}
}
