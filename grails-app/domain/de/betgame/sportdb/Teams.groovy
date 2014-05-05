package de.betgame.sportdb

class Teams {

	String key
	String title
	String title2
	String code
	String synonyms
	Integer countryId
	Integer cityId
	Integer club
	Integer since
	String address
	String web
	Integer national
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		key maxSize: 2000000000, unique: true
		title maxSize: 2000000000
		title2 nullable: true, maxSize: 2000000000
		code nullable: true, maxSize: 2000000000
		synonyms nullable: true, maxSize: 2000000000
		cityId nullable: true
		since nullable: true
		address nullable: true, maxSize: 2000000000
		web nullable: true, maxSize: 2000000000
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
	
	def String toString() {
		"$code ($title)"
	}
}
