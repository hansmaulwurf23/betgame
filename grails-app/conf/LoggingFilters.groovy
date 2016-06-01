import org.apache.log4j.MDC;
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;

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
	
	static def skippedFields = []


	def springSecurityService;

	def filters = {
		all(controller:'*', action:'*', actionExclude: 'showStatus') {
			before = {
				// USERNAME
				if (springSecurityService.isLoggedIn()) {
					if (SpringSecurityUtils.isSwitched()) {
						MDC.put('username', SpringSecurityUtils.getSwitchedUserOriginalUsername() + ">" + springSecurityService.currentUser.username)
					} else {
						MDC.put('username', springSecurityService.currentUser.username)
					}
				} else { 
					MDC.put('username', 'NA')
				}
				
				def filteredParams = [:]
				params.each { key, value ->
					if (key in hiddenFields) {
						filteredParams[key] = '[hidden]'
					} else if (!key.startsWith('columns') && !(key in skippedFields)) {
						filteredParams[key] = value
					}
				}
				log.debug(">>> request: ${controllerName}->${actionName} (${filteredParams})")
			}
			afterView = {
				
			}
		}
		
		performance(controller:'*', action:'*') {
			before = {
				request.performance = [:]
				request.performance.stopWatch = System.currentTimeMillis()
			}
			after = { returnValue ->
				request.performance.timeDiffAfter = (System.currentTimeMillis() - request.performance.stopWatch)
			}
			
			afterView = {
				request.performance.timeDiffAfterView = (System.currentTimeMillis() - request.performance.stopWatch)
				
				def controllerTime = request.performance.timeDiffAfter
				def renderingTime = request.performance.timeDiffAfterView
				log.debug("### performance (${controllerName}->${actionName}): controllerTime->${controllerTime}ms, renderingTime->${renderingTime}ms")
			}

		}
	}

}
