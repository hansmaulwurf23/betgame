package de.betgame


class Location {

	String city
	String stadium
	String groundKey
	Integer capacity

	Long fkid
	
	Date	dateCreated
	Date	lastUpdated
	
    static	mapping = {
		id column: "location_id", generator: "assigned"
		autoTimestamp true
		version false
    }
    
	static	constraints = {
		city nullable:true
		stadium nullable:true
		groundKey nullable:true
		capacity nullable:true
		dateCreated nullable:true
		lastUpdated nullable:true
		fkid nullable: true
    }
	
	public String toString() {
		return "${city} ($stadium)";
	}
}
