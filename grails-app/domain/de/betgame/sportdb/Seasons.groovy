package de.betgame.sportdb

class Seasons {

	String key
	String title
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		key maxSize: 2000000000
		title maxSize: 2000000000
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
