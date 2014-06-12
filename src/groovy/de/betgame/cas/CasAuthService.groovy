package de.betgame.cas


import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper
import org.springframework.security.core.userdetails.UserDetailsService

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.betgame.sec.*
import de.betgame.sec.SecurityService

public class CasAuthService extends UserDetailsByNameServiceWrapper {
	
	def securityService

	// cannot use log in afterPropertiesSet - build it yourself
	def logger = LoggerFactory.getLogger(CasAuthService.class);
	
	CasAuthService(UserDetailsService userDetailsService) {
		super(userDetailsService);
	}

	void afterPropertiesSet() {
		log.warn "___________________________XXXX_____________________"
		super.afterPropertiesSet();
	}

	UserDetails loadUserDetails(Authentication authentication) {

		logger.info("Authentication for principal "+authentication.getPrincipal() + " has been created.")

		def assertion = authentication.getAssertion();

		logger.info("CAS server has given us the following attibutes for this principal: ");
		logger.info("#---> "+assertion.getPrincipal().getAttributes());

		logger.info("CAS authentication validity [${assertion.getValidFromDate()} - ${assertion.getValidUntilDate()}]");

		logger.debug("At this point you have an authenticated principal(Spring Security) with attributes.")
		logger.debug("Here is the place to process application specific logic, such as automatic loading or updating of users.");

		//Enables auto update by log in
		save(authentication);

		return super.loadUserDetails(authentication);
	}

	void setUserDetailsService(UserDetailsService aUserDetailsService) {
		super.setUserDetailsService(aUserDetailsService);
	}


	private void save(authentication) {

		try {
			def attrs = authentication.getAssertion().getPrincipal().getAttributes()

			def	user = new User()
			// get the username
			user.username = authentication.getPrincipal();
			// cas users have no password
			user.password = 'CAS_NO_PASS';

			// fill other attributes
			user.email = attrs.Mail;
			user.givenname = attrs.GivenName;
			user.surname = attrs.Surname;

			// convert the CAS groups to GROW groups
			def groups = []
			if(attrs.groupMembership) {
				def gs = attrs.groupMembership;
				// groupMembership is a String so first remove the brackets
				if(gs.startsWith("["))
					gs = attrs.groupMembership.substring(1,attrs.groupMembership.length()-1);
			
				// values are space separated
				def gmSplit = gs.split(' ')
				for(grpEntrty in gmSplit) {

					String name = null;
					if(grpEntrty.startsWith("cn="))
						name = grpEntrty.substring("cn=".length(), grpEntrty.indexOf(","));

					if(name!=null) {
						groups.add("ROLE_"+name.toUpperCase())
					}
				}
			}

			logger.warn("User import/update: " + user.username);
			logger.warn("User groups import/update: " + groups)

			securityService.updateUserGroups(user,groups)
		}catch(e) {
			e.printStackTrace();
			logger.error(e.getMessage())
		}
	}
}
