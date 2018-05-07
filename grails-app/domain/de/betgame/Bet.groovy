package de.betgame

import de.betgame.sec.User

class Bet {

	User user
	Game game 
	Integer score1
	Integer score2
	
	Date	dateCreated
	Date	lastUpdated
	
    static	mapping = {
		id column: "bet_id"
		autoTimestamp true
		version false
    }
    
	static	constraints = {
		user nullable: false
		game nullable: false
		dateCreated nullable:true
		lastUpdated nullable:true
    }
	
	transient public int getScore() {
		def score = 0
		if (game.score1 != null && game.score2 != null) {
			if (game.score1 == this.score1 && game.score2 == this.score2) {
				score = 3
			} else if (game.score1 - game.score2 == this.score1 - this.score2) {
				score = 2
			} else if (Integer.signum(game.score1 - game.score2) == Integer.signum(this.score1 - this.score2)) {
				score = 1
			}
		}
		return score
	}
	
	transient public Integer getGoalDiff() {
		if (game.matchStarted) {
			return score1 - score2
		} else {
			return null
		}
	}
	
}
