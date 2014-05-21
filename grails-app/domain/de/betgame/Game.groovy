package de.betgame


class Game {

	Team team1
	Team team2
	Integer score1
	Integer score2
	Location location
	Date playAt
	Date playAtUTC
	Integer numberOfViewers
	Boolean matchIsFinished
	String groupName
	
	/* Automatic timestamping of GORM */
	Date	dateCreated
	Date	lastUpdated
	
    static	mapping = {
		datasource 'betgame'
		id column:"Game_id", generator: "assigned"
		autoTimestamp true
		version false
    }
    
	static	constraints = {
		matchIsFinished nullable: true
		score1 nullable: true
		score2 nullable: true
		dateCreated nullable: true
		lastUpdated nullable: true
		numberOfViewers nullable: true
		groupName nullable: true
    }
	
}
