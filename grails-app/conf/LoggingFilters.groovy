class LoggingFilters {

	static def hiddenFields = [
		'password',
		'oldPw',
		'newPw',
		'newPwRetype',
		'confirmPassword'
	]
	
	static def filteredActions = [
		'status'
	]


	def securityService;

	def filters = {
		all(controller:'*', action:'*', actionExclude: 'showStatus') {
			before = {
				def filteredParams = [:]
				params.each { key, value ->
					if (key in hiddenFields)
						filteredParams[key] = '[hidden]'
					else
						filteredParams[key] = value
				}
				log.debug(">>> request: ${controllerName}->${actionName} (${filteredParams})")
			}
			afterView = {
				
			}
		}
	}

}
