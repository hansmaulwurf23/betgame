package de.betgame.sportdb

class Regions {

	String name
	String key
	Integer placeId
	String code
	String abbr
	String iso
	String nuts
	String altNames
	Integer countryId
	Integer pop
	Integer area
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		name maxSize: 2000000000
		key maxSize: 2000000000
		code nullable: true, maxSize: 2000000000
		abbr nullable: true, maxSize: 2000000000
		iso nullable: true, maxSize: 2000000000
		nuts nullable: true, maxSize: 2000000000
		altNames nullable: true, maxSize: 2000000000
		pop nullable: true
		area nullable: true
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
