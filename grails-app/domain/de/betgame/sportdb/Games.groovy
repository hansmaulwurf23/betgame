package de.betgame.sportdb

import java.text.SimpleDateFormat;

class Games {

	String key
	Integer roundId
	Integer pos
	Groups group
	Teams team1
	Teams team2
	Date playAt
	Integer postponed
	Grounds ground
	Integer cityId
	Integer knockout
	Integer home
	Integer score1
	Integer score2
	Integer score1et
	Integer score2et
	Integer score1p
	Integer score2p
	Integer score1i
	Integer score2i
	Integer score1ii
	Integer score2ii
	Integer nextGameId
	Integer prevGameId
	Integer winner
	Integer winner90
	Date createdAt
	Date updatedAt

	static mapping = {
		id generator: "assigned"
		version false
		team1 column:'team1_id'
		team2 column:'team2_id'
		group column:'group_id'
		ground column:'ground_id'
	}

	static constraints = {
		group nullable: true
		ground nullable: true
		team1()
		team2()
		key nullable: true, maxSize: 2000000000
		cityId nullable: true
		score1 nullable: true
		score2 nullable: true
		score1et nullable: true
		score2et nullable: true
		score1p nullable: true
		score2p nullable: true
		score1i nullable: true
		score2i nullable: true
		score1ii nullable: true
		score2ii nullable: true
		nextGameId nullable: true
		prevGameId nullable: true
		winner nullable: true
		winner90 nullable: true
	}
	
	def getLocalPlayAt() {
		if (playAt && ground && ground.timezone) {
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(ground.timezone))
			cal.set(year: playAt.year +1900, month: playAt.month, date: playAt.date, hourOfDay: playAt.hours, minute: playAt.minutes)
			return cal.time
		} else {
			return null
		}
	}
	
}
