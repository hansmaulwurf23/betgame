import de.betgame.sec.SecurityService
// Place your Spring DSL code here
beans = {
	
	/*
	 * CAS
	 */
	authenticationUserDetailsService(de.betgame.cas.CasAuthService, ref('userDetailsService')) {
		// KrZ remember me :D
		securityService = ref('securityService')
	}
	
}
