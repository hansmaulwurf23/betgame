<%@ page import="org.quartz.Trigger" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>Quartz Jobs</title>
        
		<asset:stylesheet src="quartz.css"/>
		<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>

        <script>
        $(document).ready(function () {
            $('.mainJobCol').each(function(idx, elem) {
                console.log(idx);
                $.getJSON("${createLink(action:'status')}", {jobName:$(this).attr('data-jobname')}, function(retVal) {
                    if (retVal.status == 'OK') {
                        console.log(retVal.jobStatus);
                    }
                })
            });
        });
    </script>
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
            <div id="clock" data-time="${now.time}">
                <h3>Current Time: ${now}</h3>
            </div>
            <div class="list">
                <table id="quartz-jobs" class="table">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Next Run</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${jobs}" status="i" var="jobName">
                        <tr>
                            <g:set var="jobData" value="${jobsData[jobName]}" />
                            <td class="mainJobCol" data-jobname="${jobName}">${jobName}</td>
                            <g:if test="${scheduler.isInStandbyMode() || jobData.triggerStatus == Trigger.TriggerState.PAUSED}">
                                <td class="hasCountdown countdown_amount">Paused</td>
                            </g:if>
                            <g:else>
                                <td class="quartz-countdown" data-next-run="${jobData.trigger?.nextFireTime?.time ?: ""}">${jobData.trigger?.nextFireTime}</td>
                            </g:else>
                            <td class="quartz-actions">
                                <g:if test="${jobData?.status != 'running'}">
                                    <g:if test="${jobData?.trigger}">
                                        <a href="<g:createLink action="stop" params="[jobName:jobName, triggerName:jobData?.trigger.name, triggerGroup:jobData?.trigger.group]"/>">
                                        <asset:image class="quartz-tooltip" data-tooltip="Stop job from running again" src="stop.png" /></a>
                                    </g:if>
                                    <g:else>
                                        <a href="<g:createLink action="start" params="[jobName:jobName]"/>">
                                        <asset:image class="quartz-tooltip" data-tooltip="Start job schedule" src="start.png" /></a>
                                    </g:else>
                                    <a href="<g:createLink action="runNow" params="[jobName:jobName]"/>">
                                    <asset:image class="quartz-tooltip" data-tooltip="Run now" src="run.png" />
                                    </a>
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
