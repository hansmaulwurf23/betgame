package de.betgame

class Team {

	String name
	String iconUrl
	String code
	String net
	String groupName
	
	Date	dateCreated
	Date	lastUpdated
	
    static	mapping = {
		id column: "team_id", generator: "assigned"
		autoTimestamp true
		version false
    }
    
	static	constraints = {
		iconUrl nullable: true
		code nullable: true
		dateCreated nullable: true
		lastUpdated nullable: true
		net nullable: true
		groupName nullable: true
    }
	
}
