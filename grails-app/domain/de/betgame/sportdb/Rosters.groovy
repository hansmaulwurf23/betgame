package de.betgame.sportdb

class Rosters {

	Integer personId
	Integer teamId
	Integer eventId
	Integer pos
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		eventId nullable: true
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
