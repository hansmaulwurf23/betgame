package de.betgame.sportdb

class Goals {

	Integer personId
	Integer gameId
	Integer minute
	Integer offset
	Integer score1
	Integer score2
	Integer penalty
	Integer owngoal
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		minute nullable: true
		offset nullable: true
		score1 nullable: true
		score2 nullable: true
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
