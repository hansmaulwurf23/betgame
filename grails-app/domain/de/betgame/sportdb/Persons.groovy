package de.betgame.sportdb

class Persons {

	String key
	String name
	String synonyms
	String code
	String bornAt
	Integer cityId
	Integer regionId
	Integer countryId
	Integer nationalityId
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		key maxSize: 2000000000
		name maxSize: 2000000000
		synonyms nullable: true, maxSize: 2000000000
		code nullable: true, maxSize: 2000000000
		bornAt nullable: true, maxSize: 2000000000
		cityId nullable: true
		regionId nullable: true
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
