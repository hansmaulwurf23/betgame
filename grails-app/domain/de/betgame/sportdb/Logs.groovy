package de.betgame.sportdb

class Logs {

	String msg
	String level
	String app
	String tag
	Integer pid
	Integer tid
	String ts
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		msg maxSize: 2000000000
		level maxSize: 2000000000
		app nullable: true, maxSize: 2000000000
		tag nullable: true, maxSize: 2000000000
		pid nullable: true
		tid nullable: true
		ts nullable: true, maxSize: 2000000000
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
