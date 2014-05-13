package de.betgame.sportdb

class EventsGrounds {

	Events event
	Grounds ground
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
		event column:'event_id'
		ground column:'ground_id'
	}

	static constraints = {
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
