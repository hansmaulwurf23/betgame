package de.betgame.sportdb

class Races {

	Integer trackId
	Integer eventId
	Integer pos
	String startAt
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		startAt nullable: true, maxSize: 2000000000
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
