package de.betgame.sportdb

class EventsGrounds {

	Integer eventId
	Integer groundId
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
