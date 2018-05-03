package betgame

import grails.plugin.springsecurity.SpringSecurityUtils
import org.apache.commons.lang.time.StopWatch
import org.slf4j.MDC


class LoggingInterceptor {

    static def hiddenFields = [
            'password',
    ]

    def springSecurityService
    
    LoggingInterceptor() {
        matchAll().excludes(controller:"assets")
    }

    boolean before() {
        if (springSecurityService.isLoggedIn()) {
            if (SpringSecurityUtils.isSwitched()) {
                MDC.put('username', SpringSecurityUtils.getSwitchedUserOriginalUsername() + ">" +springSecurityService.authentication?.principal?.username)
            } else {
                MDC.put('username', springSecurityService.authentication?.principal?.username)
            }
        } else {
            MDC.put('username', 'NA')
        }

        def filteredParams = [:]
        params.each { k, v ->
            if (k in hiddenFields) {
                filteredParams[k] = '[hidden] (length=${v.length()})'
            } else {
                filteredParams[k] = v
            }
        }

        request.id = new Date().time
        log.debug ">>> request (${request.id}): ${controllerName}->${actionName} (${filteredParams})"
        request.performance = [:]
        request.performance.stopWatch = new StopWatch()
        request.performance.stopWatch.start()
        true
    }

    boolean after() {
        request.performance.timeDiffAfter = request.performance.stopWatch.getTime()
        true
    }

    void afterView() {
        request.performance.stopWatch.stop()
        request.performance.timeDiffAfterView = request.performance.stopWatch.getTime()

        log.debug "### performance (${request.id}): controllerTime->${request.performance.timeDiffAfter}ms, renderingTime->${request.performance.timeDiffAfterView}ms"

        MDC.remove('username')
    }
}
