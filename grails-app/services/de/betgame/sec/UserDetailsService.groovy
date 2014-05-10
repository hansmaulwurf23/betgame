package de.betgame.sec

import grails.plugin.springsecurity.userdetails.GormUserDetailsService;
import grails.transaction.Transactional

import org.springframework.security.core.userdetails.UserDetails

/**
 * UserDetailsService
 * A service class encapsulates the core business logic of a Grails application
 */
@Transactional
class UserDetailsService extends GormUserDetailsService {

    UserDetails loadUserByUsername(String username) {
		log.warn "Looooooking for a user with username $username"
		User u = User.findByUsername(username)
		return u
    }
	
}
