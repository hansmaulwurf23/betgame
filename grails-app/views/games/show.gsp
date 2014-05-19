
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
<div class="panel panel-default">
	
	<div class="panel-heading">
	<div class="row">
		<div class="col-xs-6">
			<g:formatDate date="${gamesInstance?.playAt}" type="date" dateStyle="SHORT" /> 
			<g:formatDate date="${gamesInstance?.playAt}" type="time" timeStyle="SHORT" />
		</div>
  		<div class="col-xs-6 text-right"><g:link controller="grounds" action="show" id="${gamesInstance?.group?.id}">${gamesInstance?.group?.encodeAsHTML()}</g:link></div>
  	</div>
	</div>
	
  	<div class="row">
		<div class="col-xs-12 text-center">@ <g:link controller="grounds" action="show" id="${gamesInstance?.ground?.id}">${gamesInstance?.ground?.encodeAsHTML()}</g:link></div>
	</div>
	
	<g:render template="gameDivRow" />
	
	<div class="row">
		<g:if test="${myBet}">
			<div class="col-xs-12 text-center">${myBet.score1} : ${myBet.score2}</div>
		</g:if>
		<g:else>
			<div class="col-xs-12 text-center">
			<sec:ifNotLoggedIn>
				<g:link controller='login' action='auth'><g:message code="not.logged.in" default="Not Logged In" /></g:link>
			</sec:ifNotLoggedIn>
			<sec:ifLoggedIn>
				<g:link controller="bet" action='create' params="[gameid: gamesInstance.id]" ><g:message code="no.bet" default="No bet yet" /></g:link>
			</sec:ifLoggedIn>
			</div>
		</g:else>
	</div>
	
	<div class="panel-footer">
	<div class="row">
		<div class="col-xs-12"><small>updated: <g:formatDate date="${gamesInstance?.updatedAt}" /></small></div>
	</div>
	</div>
</div>

</div>
</section>

</body>

</html>
