package de.betgame.sportdb

class Tags {

	String key
	String slug
	String name
	Integer grade
	Integer parentId
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		key maxSize: 2000000000, unique: true
		slug maxSize: 2000000000
		name nullable: true, maxSize: 2000000000
		parentId nullable: true
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
