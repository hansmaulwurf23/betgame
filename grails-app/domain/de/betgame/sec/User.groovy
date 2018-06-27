package de.betgame.sec

class User {

	transient springSecurityService

	String username
	String password
	String email
	String givenname
	String surname
	String display
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	boolean nomail

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
		email nullable: true
		givenname nullable: true
		surname nullable: true
		display nullable: true
	}

	static mapping = {
		table name: 'users', schema:'sec'
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

}
