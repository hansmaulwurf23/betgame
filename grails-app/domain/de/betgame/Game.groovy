package de.betgame

import grails.converters.JSON;


class Game {

	Team team1
	Team team2
	Integer score1
	Integer score2
	Integer finalScore1
	Integer finalScore2
	Location location
	Date playAt
	Date playAtUTC
	Integer numberOfViewers
	Boolean matchIsFinished
	String goals
	String phase

	Long fkid
	
	Date	dateCreated
	Date	lastUpdated
	
    static	mapping = {
		id column:"Game_id", generator: "assigned"
		autoTimestamp true
		goals type:'text'
		finalScore1 column:'fscore1'
		finalScore2 column:'fscore2'
		version false
    }
    
	static	constraints = {
		matchIsFinished nullable: true
		score1 nullable: true
		score2 nullable: true
		finalScore1 nullable: true
		finalScore2 nullable: true
		dateCreated nullable: true
		lastUpdated nullable: true
		numberOfViewers nullable: true
		location nullable: true
		goals nullable: true
		phase nullable: true
		fkid nullable: true
    }
	
	transient goalInfos() {
		if (goals) {
			def goalsList = JSON.parse(goals)
			if (goalsList) {
				if (goalsList.find { it.minute }) {
					return goalsList?.sort { it.minute?.toInteger() }
				} else {
					return goalsList?.sort { (it.score1 ?: 0) + (it.score2 ?: 0) }
				}
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
	
	transient boolean isSameAsFinalScore() {
		return (score1 == finalScore1 && score2 == finalScore2)
	}
}
