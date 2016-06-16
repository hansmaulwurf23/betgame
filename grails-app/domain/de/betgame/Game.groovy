package de.betgame

import grails.converters.JSON;


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
	String goals
	String phase
	
	/* Automatic timestamping of GORM */
	Date	dateCreated
	Date	lastUpdated
	
    static	mapping = {
		datasource 'betgame'
		id column:"Game_id", generator: "assigned"
		autoTimestamp true
		goals type:'text'
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
		location nullable: true
		goals nullable: true
		phase nullable: true
    }
	
	transient goalInfos() {
		if (goals) {
			def goalsList = JSON.parse(goals)
			if (goalsList) {
				goalsList = goalsList.findAll { it.minute }
				return goalsList?.sort { it.minute?.toInteger() }
			} else {
				return null
			}
		} else {
			return null
		}  
	}
	
	transient boolean isMatchStarted() {
		return (playAt < new Date())
	} 
}
