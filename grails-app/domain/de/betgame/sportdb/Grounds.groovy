package de.betgame.sportdb

class Grounds {

	String key
	String title
	String synonyms
	Integer countryId
	Cities city
	Integer since
	Integer capacity
	String address
	String timezone
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		city column:'city_id'
		version false
	}

	static constraints = {
		key maxSize: 2000000000, unique: true
		title maxSize: 2000000000
		synonyms nullable: true, maxSize: 2000000000
		city nullable: true
		since nullable: true
		capacity nullable: true
		address nullable: true, maxSize: 2000000000
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
	
	def String toString() {
		title
	}
}
