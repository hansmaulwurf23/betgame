package de.betgame.sportdb

class EventsTeams {

	Events event
	Teams team
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
		event column:'event_id'
		team column:'team_id'
	}

	static constraints = {
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
