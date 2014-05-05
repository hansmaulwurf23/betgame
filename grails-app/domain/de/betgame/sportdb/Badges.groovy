package de.betgame.sportdb

class Badges {

	Integer teamId
	Integer leagueId
	Integer seasonId
	String title
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		title maxSize: 2000000000
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
