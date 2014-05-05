package de.betgame.sportdb

class Props {

	String key
	String value
	String kind
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		key maxSize: 2000000000
		value maxSize: 2000000000
		kind nullable: true, maxSize: 2000000000
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
