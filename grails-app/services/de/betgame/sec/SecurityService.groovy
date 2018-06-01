package de.betgame.sec

import grails.transaction.Transactional
import org.springframework.security.core.context.SecurityContextHolder;

import grails.plugin.springsecurity.SpringSecurityUtils;

@Transactional
class SecurityService {

	def getPrincipal() {
		return SecurityContextHolder.context.authentication.principal
	}

	def getUsername() {
		return SecurityContextHolder.context.authentication.principal.username
	}
	
	def getRoles() {
		return SpringSecurityUtils.getPrincipalAuthorities()
	}

	def hasRole(role) {
		return SpringSecurityUtils.ifAnyGranted(role)
	}
	
	def userAuthorities(userId) {
		User.findByUsername(userId)?.authorities?.authority;
	}
	
    def findOrCreateRole(def authority) {
		Role.findByAuthority(authority) ?: new Role(authority: authority).save(failOnError: true, flush:true)
    }
	
	def updateUserGroups(user, groups) {
		def dbUser = User.findByUsername(user.username) ?: new User(
			username: user.username,
			password: 'CAS_NO_PASS',
			email: user.email,
			givenname: user.givenname,
			surname: user.surname,
			display: user.givenname,
			enabled: true).save(failOnError: true)
			
			
		def oldGroups = dbUser.authorities.authority
		
		groups.add('ROLE_USER')
		
		groups.each { rolename ->
			if (!oldGroups.contains(rolename)) {
				def role = findOrCreateRole(rolename)
                if (!UserRole.findByUserAndRole(dbUser, role)) {
                    new UserRole(user: dbUser, role: role).save(flush: true, insert: true, failOnError: true)
                }
			}
		}
		
		oldGroups.each { oldGroup ->
			if(!groups.contains(oldGroup)) {
				def userRole = findOrCreateRole(oldGroup)
				UserRole.remove(dbUser, userRole)
			}
		}
		
		return dbUser
	}
}
