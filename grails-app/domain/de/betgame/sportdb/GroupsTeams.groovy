package de.betgame.sportdb

class GroupsTeams {

	Groups group
	Teams team
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
		group column:'group_id'
		team column:'team_id'
	}

	static constraints = {
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
