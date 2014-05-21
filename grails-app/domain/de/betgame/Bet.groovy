package de.betgame

import de.betgame.sec.User

class Bet {

	/* Default (injected) attributes of GORM */
//	Long	id
//	Long	version
	User user
	Game game 
	Integer score1
	Integer score2
	
	
	/* Automatic timestamping of GORM */
	Date	dateCreated
	Date	lastUpdated
	
	
    static	mapping = {
		datasource 'betgame'
		id column: "bet_id"
		autoTimestamp true
		version false
    }
    
	static	constraints = {
		user nullable: false
		game nullable: false
		dateCreated nullable:true
		lastUpdated nullable:true
    }
	
	/*
	 * Methods of the Domain Class
	 */
//	@Override	// Override toString for a nicer / more descriptive UI 
//	public String toString() {
//		return "${name}";
//	}
}
