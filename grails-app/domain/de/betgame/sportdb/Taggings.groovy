package de.betgame.sportdb

class Taggings {

	Integer tagId
	Integer taggableId
	String taggableType
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
	}

	static constraints = {
		taggableType maxSize: 2000000000
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
}
