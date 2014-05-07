package de.betgame.sportdb

class Groups {

	Events event
	String title
	Integer pos
	String createdAt
	String updatedAt

	static mapping = {
		id generator: "assigned"
		version false
		event column:'event_id'
	}

	static constraints = {
		title maxSize: 2000000000
		createdAt maxSize: 2000000000
		updatedAt maxSize: 2000000000
	}
	
	def String toString() {
		title
	}
}
