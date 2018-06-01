package de.betgame.cas


import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper
import org.springframework.security.core.userdetails.UserDetailsService

import org.slf4j.LoggerFactory

import de.betgame.sec.*

class CasAuthService extends UserDetailsByNameServiceWrapper {

    def securityService

    // cannot use log in afterPropertiesSet - build it yourself
    def logger = LoggerFactory.getLogger(CasAuthService.class)

    CasAuthService(UserDetailsService userDetailsService) {
        super(userDetailsService)
    }

    void afterPropertiesSet() {
        super.afterPropertiesSet()
    }

    UserDetails loadUserDetails(Authentication authentication) {

        logger.info("Authentication for principal "+authentication.getPrincipal() + " has been created.")

        def assertion = authentication.getAssertion()

        logger.info("CAS server has given us the following attibutes for this principal: ")
        logger.info("#---> "+assertion.getPrincipal().getAttributes().collect { "${it.key}: ${it.value}" }.join('\n'))

        logger.info("CAS authentication validity [${assertion.getValidFromDate()} - ${assertion.getValidUntilDate()}]")

        //Enables auto update by log in
        save(authentication)

        return super.loadUserDetails(authentication)
    }

    void setUserDetailsService(UserDetailsService aUserDetailsService) {
        super.setUserDetailsService(aUserDetailsService)
    }


    private void save(authentication) {

        try {
            def attrs = authentication.getAssertion().getPrincipal().getAttributes()

            def	user = new User()
            // get the username
            user.username = authentication.getPrincipal()
            // cas users have no password
            user.password = 'CAS_NO_PASS'
            user.email = attrs.mail
            def names = attrs.displayName?.tokenize(',')*.trim()
            user.givenname = names[1]
            user.surname = names[0]

            // convert the CAS groups to GROW groups
            def groups = attrs.groups?.collect { "ROLE_${it?.toUpperCase()}" }

            logger.warn("User import/update: " + user.username)
            logger.warn("User groups import/update: " + groups)

            securityService.updateUserGroups(user,groups)
        }catch(e) {
            e.printStackTrace()
            logger.error(e.getMessage())
        }
    }
}