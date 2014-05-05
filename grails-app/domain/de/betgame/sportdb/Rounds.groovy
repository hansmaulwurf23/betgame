package de.betgame.sportdb

class Rounds {

	Integer eventId
	String title
	String title2
	Integer pos
	Integer knockout
	String startAt
	String endAt
	Integer auto
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		title maxSize: 2000000000
		title2 nullable: true, maxSize: 2000000000
		startAt maxSize: 2000000000
		endAt nullable: true, maxSize: 2000000000
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
