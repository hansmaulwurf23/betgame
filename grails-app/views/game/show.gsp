
<%@ page import="de.betgame.Game" %>
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
<div class="panel panel-default">
	
	<div class="panel-heading">
	<div class="row">
		<div class="col-xs-6">
			<g:formatDate date="${gameInstance?.playAt}" type="date" dateStyle="SHORT" />
			<g:formatDate date="${gameInstance?.playAt}" type="time" timeStyle="SHORT" />
		</div>
  		<div class="col-xs-6 text-right"><g:link action="list" params="[ground: gameInstance?.groupName]">Gruppe ${gameInstance?.groupName}</g:link></div>
  	</div>
	</div>
	
  	<div class="row">
		<div class="col-xs-12 text-center">@ <g:link controller="location" action="show" id="${gameInstance?.location?.id}">${gameInstance?.location?.encodeAsHTML()}</g:link></div>
	</div>
	
	<g:render template="gameDivRow" />
	
	<div class="row">
		<g:if test="${myBet}">
			<div class="col-xs-12 text-center"><g:link controller="bet" action='edit' id="${myBet.id}">${myBet.score1}:${myBet.score2}</g:link></div>
		</g:if>
		<g:else>
			<div class="col-xs-12 text-center">
			<sec:ifNotLoggedIn>
				<g:link controller='login' action='auth'><g:message code="not.logged.in" default="Nicht angemeldet" /></g:link>
			</sec:ifNotLoggedIn>
			<sec:ifLoggedIn>
				<g:link controller="bet" action='create' params="['game.id': gameInstance.id]" ><g:message code="no.bet" default="Noch kein Tipp abegeben" /></g:link>
			</sec:ifLoggedIn>
			</div>
		</g:else>
	</div>
	
	<div class="panel-footer">
	<div class="row">
		<div class="col-xs-12"><small>updated: <g:formatDate type="datetime" date="${gameInstance?.lastUpdated}" /></small></div>
	</div>
	</div>
</div>

</div>
</section>

</body>

</html>
