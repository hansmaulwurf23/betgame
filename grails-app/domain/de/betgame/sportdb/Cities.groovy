package de.betgame.sportdb

class Cities {

	String name
	String key
	Integer placeId
	String code
	String altNames
	Integer countryId
	Integer regionId
	Integer cityId
	Integer pop
	Integer popm
	Integer area
	Integer m
	Integer c
	Integer d
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
		altNames nullable: true, maxSize: 2000000000
		regionId nullable: true
		cityId nullable: true
		pop nullable: true
		popm nullable: true
		area nullable: true
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
