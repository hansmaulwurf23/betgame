package de.betgame.sportdb

class Events {

	String key
	Integer leagueId
	Integer seasonId
	String startAt
	String endAt
	Integer team3
	String sources
	String config
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		key maxSize: 2000000000, unique: true
		startAt maxSize: 2000000000
		endAt nullable: true, maxSize: 2000000000
		sources nullable: true, maxSize: 2000000000
		config nullable: true, maxSize: 2000000000
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
