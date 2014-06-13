<%@page import="de.betgame.Game"%>
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
		<g:set var="gameInstance" value="${betInstance.game}" />
		<g:render template="/game/gameDivRow" />
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

<g:message code="back.to" default="Back to"/> <g:link controller="game" action="list" params="[group:gameInstance.groupName]"><g:message code="group" default="Group"/> ${gameInstance.groupName}</g:link>

</section>

</body>

</html>
