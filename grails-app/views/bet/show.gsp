<%@page import="de.betgame.Game"%>
<%@ page import="de.betgame.Bet" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'bet.label', default: 'Bet')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-bet" class="first">


<div class="card ">
	
	<div class="card-header">
		<g:set var="game" value="${bet.game}" />
		<g:render template="/game/gameDivRow" />
	</div>

	<g:if test="${!bet.game.sameAsFinalScore}">
		<div class="row card-body text-center">
			(${bet.game?.score1}:${bet.game?.score2})
		</div>
	</g:if>
	
	<div class="row card-body">
		<div class="col col-xs-5 text-right">
			${bet.score1}
		</div>
		<div class="col col-xs-2 text-center"> : </div>
		<div class="col col-xs-5">
			${bet.score2}
		</div>
	</div>
	
	<div class="row card-body text-center">
		<bg:score bet="${bet}" />
	</div>
	
	<div class="card-footer">
		<div class="row">
			<div class="col col-xs-12">
				<g:link controller="game" action="show" id="${bet.game.id}">zu Spiel wechseln</g:link>
			</div>
		</div>
	</div>

</div>

<g:message code="back.to" default="Back to"/> <g:link controller="game" action="list" params="[phase:game.phase]"><g:message code="group" default="Group"/> ${game.phase}</g:link>

</section>

</body>

</html>
