package de.betgame.sportdb

class Countries {

	String name
	String slug
	String key
	Integer placeId
	String code
	String altNames
	Integer pop
	Integer area
	Continents continent
	Integer countryId
	Integer s
	Integer c
	Integer d
	String motor
	String iso2
	String iso3
	String fifa
	String net
	String wikipedia
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		continent column:'continent_id'
		version false
	}

	static constraints = {
		name maxSize: 2000000000
		slug maxSize: 2000000000
		key maxSize: 2000000000
		code maxSize: 2000000000
		altNames nullable: true, maxSize: 2000000000
		continent nullable: true
		countryId nullable: true
		motor nullable: true, maxSize: 2000000000
		iso2 nullable: true, maxSize: 2000000000
		iso3 nullable: true, maxSize: 2000000000
		fifa nullable: true, maxSize: 2000000000
		net nullable: true, maxSize: 2000000000
		wikipedia nullable: true, maxSize: 2000000000
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
