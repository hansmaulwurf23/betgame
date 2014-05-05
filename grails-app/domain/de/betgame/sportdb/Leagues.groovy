package de.betgame.sportdb

class Leagues {

	String key
	String title
	Integer countryId
	Integer club
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		key maxSize: 2000000000
		title maxSize: 2000000000
		countryId nullable: true
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
