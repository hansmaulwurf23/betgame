package de.betgame.sportdb

class Records {

	Integer raceId
	Integer runId
	Integer personId
	Integer pos
	Integer completed
	String state
	String comment
	String time
	String timeline
	Integer laps
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		raceId nullable: true
		runId nullable: true
		pos nullable: true
		state nullable: true, maxSize: 2000000000
		comment nullable: true, maxSize: 2000000000
		time nullable: true, maxSize: 2000000000
		timeline nullable: true, maxSize: 2000000000
		laps nullable: true
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
