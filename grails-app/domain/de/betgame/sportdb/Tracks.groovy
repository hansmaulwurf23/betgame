package de.betgame.sportdb

class Tracks {

	String key
	String title
	String synonyms
	String code
	Integer cityId
	Integer regionId
	Integer countryId
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		key maxSize: 2000000000
		title maxSize: 2000000000
		synonyms nullable: true, maxSize: 2000000000
		code nullable: true, maxSize: 2000000000
		cityId nullable: true
		regionId nullable: true
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
