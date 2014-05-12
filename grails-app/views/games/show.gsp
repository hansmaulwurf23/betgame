
<%@ page import="de.betgame.sportdb.Games" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'games.label', default: 'Games')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
	
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
</head>

<body>

<ul id="Menu" class="nav nav-pills margin-top-small">
	<li class="${ params.action == "list" ? 'active' : '' }">
		<g:link action="list"><i class="glyphicon glyphicon-th-list"></i> <g:message code="default.list.label" args="[entityName]"/></g:link>
	</li>
</ul>

<section id="show-games" class="first">
<div class="container">
	<div class="row">
		<div class="col-xs-4 text-right">
			<g:formatDate date="${gamesInstance?.playAt}" type="date" dateStyle="SHORT" /> 
			<g:formatDate date="${gamesInstance?.playAt}" type="time" timeStyle="SHORT" />
		</div>
  		<div class="col-xs-4 text-center"><g:link controller="grounds" action="show" id="${gamesInstance?.ground?.id}">${gamesInstance?.ground?.encodeAsHTML()}</g:link></div>
  		<div class="col-xs-4"><g:link controller="grounds" action="show" id="${gamesInstance?.group?.id}">${gamesInstance?.group?.encodeAsHTML()}</g:link></div>
	</div>
	
	<div class="row">
		<div class="col-xs-5 text-right"><g:link controller="teams" action="show" id="${gamesInstance?.team1?.id}">${gamesInstance?.team1?.encodeAsHTML()}</g:link></div>
		<div class="col-xs-2 text-center">${gamesInstance.score1} : ${gamesInstance.score2}</div>
		<div class="col-xs-5"><g:link controller="teams" action="show" id="${gamesInstance?.team2?.id}">${gamesInstance?.team2?.encodeAsHTML()}</g:link></div>
	</div>
	
	<div class="row">
		<div class="col-xs-12"><small>updated: <g:formatDate date="${gamesInstance?.updatedAt}" /></small></div>
	</div>
</div>
</section>

</body>

</html>
