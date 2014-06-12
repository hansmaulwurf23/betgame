package de.betgame

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;



class LogoutController {

	def beforeInterceptor = { session.zg = 'home' }
	
	static navigation = [
		'*': [
			visible: {SpringSecurityUtils.getPrincipalAuthorities() - 'ROLE_ANONYMOUS'},
			linkclasses:['logout'],
			order: 0,
			]
		]
	
	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
		redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
	}
	
	def loggedout = { 
		redirect controller:'home'
	}
}
