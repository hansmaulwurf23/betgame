package de.betgame.sportdb

class Names {

	String name
	Integer placeId
	String lang
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		name maxSize: 2000000000
		lang maxSize: 2000000000
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
