package de.betgame.sec

class Role {

	String authority

	static mapping = {
		cache true
		table schema:'sec'
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
