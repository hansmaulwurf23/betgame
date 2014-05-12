package de.betgame.sportdb

class Continents {

	String name
	String slug
	String key
	Integer placeId
	String altNames
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		name maxSize: 2000000000
		slug maxSize: 2000000000
		key maxSize: 2000000000, unique: true
		altNames nullable: true, maxSize: 2000000000
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
	
	public String toString() {
		return name
	}
}
