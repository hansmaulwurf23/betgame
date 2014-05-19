<%@page import="de.betgame.sportdb.Games"%>
<%@ page import="de.betgame.Bet" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'bet.label', default: 'Bet')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-bet" class="first">


<div class="panel panel-default">
	
	<div class="panel-heading">
		<g:set var="gamesInstance" value="${Games.get(betInstance.gameid)}" />
		<g:render template="/games/gameDivRow" />
	</div>

	<div class="row panel-body">	
		<div class="col-xs-5 text-right">
			${betInstance.score1}
		</div>
		<div class="col-xs-2 text-center"> : </div>
		<div class="col-xs-5">
			${betInstance.score2}
		</div>
	</div>

</div>

<g:link controller="games" action="list" params="[groupId:gamesInstance.group.id]">${gamesInstance.group}</g:link>

</section>

</body>

</html>
