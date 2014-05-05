package de.betgame.sportdb

class Places {

	String name
	String kind
	Double lat
	Double lng
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		name maxSize: 2000000000
		kind maxSize: 2000000000
		lat nullable: true, scale: 10
		lng nullable: true, scale: 10
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
