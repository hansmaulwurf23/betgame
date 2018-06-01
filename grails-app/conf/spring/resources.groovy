// Place your Spring DSL code here
beans = {
    authenticationUserDetailsService(de.betgame.cas.CasAuthService, ref('userDetailsService')) {
        securityService = ref('securityService')
    }
}
