package de.betgame.sportdb

class Usages {

	Integer countryId
	Integer langId
	Integer official
	Integer minor
	Double percent
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		percent nullable: true, scale: 10
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
