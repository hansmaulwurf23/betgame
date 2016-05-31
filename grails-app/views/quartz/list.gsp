<%@ page import="org.quartz.Trigger" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Quartz Jobs</title>
        
		<asset:stylesheet src="quartz.css"/>
		<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
    </head>
    <body>
        <div class="body">
            <h1 id="quartz-title">
                Quartz Jobs
                <g:if test="${scheduler.isInStandbyMode()}">
                    <a href="${createLink(action:'startScheduler')}">
                    <asset:image class="quartz-tooltip" data-tooltip="Start scheduler" src="play-all.png" />
                    </a>
                </g:if>
                <g:else>
                    <a href="${createLink(action:'stopScheduler')}">
                    <asset:image class="quartz-tooltip" data-tooltip="Pause scheduler" src="pause-all.png" />
                    </a>
                </g:else>
            </h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div id="clock" data-time="${now.time}">
                <h3>Current Time: ${now}</h3>
            </div>
            <div class="list">
                <table id="quartz-jobs" class="table">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th class="quartz-to-hide">Result</th>
                            <th>Next Run</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${jobs}" status="i" var="job">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>${job.name?.split('\\.')[-1].replace('Job', '')}</td>
                            <td class="quartz-to-hide">${tooltip}</td>
                            <g:if test="${scheduler.isInStandbyMode() || job.triggerStatus == Trigger.TriggerState.PAUSED}">
                                <td class="hasCountdown countdown_amount">Paused</td>
                            </g:if>
                            <g:else>
                                <td class="quartz-countdown" data-next-run="${job.trigger?.nextFireTime?.time ?: ""}">${job.trigger?.nextFireTime}</td>
                            </g:else>
                            <td class="quartz-actions">
                                <g:if test="${job.status != 'running'}">
                                    <g:if test="${job.trigger}">
                                        <a href="<g:createLink action="stop" params="[jobName:job.name, triggerName:job.trigger.name, triggerGroup:job.trigger.group]"/>">
                                        <asset:image class="quartz-tooltip" data-tooltip="Stop job from running again" src="stop.png" /></a>
                                        <g:if test="${job.triggerStatus == Trigger.TriggerState.PAUSED}">
                                            <a href="<g:createLink action="resume" params="[jobName:job.name, jobGroup:job.group]"/>">
                                            <asset:image class="quartz-tooltip" data-tooltip="Resume job schedule" src="resume.png" />
                                            </a>
                                        </g:if>
                                        <g:elseif test="${job.trigger.mayFireAgain()}">
                                            <a href="<g:createLink action="pause" params="[jobName:job.name, jobGroup:job.group]"/>">
                                            <asset:image class="quartz-tooltip" data-tooltip="Pause job schedule" src="pause.png" />
                                            </a>
                                        </g:elseif>
                                    </g:if>
                                    <g:else>
                                        <a href="<g:createLink action="start" params="[jobName:job.name, jobGroup:job.group]"/>">
                                        <asset:image class="quartz-tooltip" data-tooltip="Start job schedule" src="start.png" /></a>
                                    </g:else>
                                    <a href="<g:createLink action="runNow" params="[jobName:job.name, jobGroup:job.group]"/>">
                                    <asset:image class="quartz-tooltip" data-tooltip="Run now" src="run.png" />
                                    </a>
                                    <g:if test="${job.trigger instanceof org.quartz.CronTrigger}">
                                        <a href="<g:createLink action="editCronTrigger" params="[triggerName:job.trigger.name, triggerGroup:job.trigger.group]"/>">
                                        <asset:image class="quartz-tooltip" data-tooltip="Reschedule" src="reschedule.png" /></a>
                                    </g:if>
                                </g:if>
                            </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
        <g:unless test="${grailsApplication.config.quartz.monitor.showCountdown == false}">
        	<asset:javascript src="quartz/jquery.countdown.js"/>
        	<asset:javascript src="quartz/jquery.color.js"/>
        </g:unless>
        <g:unless test="${grailsApplication.config.quartz.monitor.showTickingClock == false}">
        	<asset:javascript src="quartz/jquery.clock.js"/>
        </g:unless>
        <asset:javascript src="quartz/quartz-monitor.js"/>
    </body>
</html>
