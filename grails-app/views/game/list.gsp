
<%@ page import="de.betgame.Game" %>
<%@ page import="de.betgame.NameUtil" %>

<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<title><g:message code="games.label" /></title>
</head>

<body>
<ul id="Menu" class="nav nav-pills margin-top-small groups">
<g:each in="${phases}" var="p">
	<li class="nav-item">
		<g:link class="nav-link ${ p == phase ? 'active' : '' }" action="list" params="[phase:p]">${p}</g:link>
	</li>
</g:each>
</ul>

<section id="list-game" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
		</thead>
		<tbody>
		<g:each in="${games}" status="i" var="game">
			<tr class="${game.matchIsFinished ? 'active' : ''}">
				<g:set var="notbet" value="${!(game.id in gameIDsFromBets) }" />
				<td><g:link action="show" id="${game.id}" class="${notbet ? 'text-danger' : ''}">
					<g:formatDate date="${game?.playAt}" formatName="default.gamedate.format" />${notbet ? '!' : ''}
				</g:link></td>
				<g:if test="${!group}">
					<g:set var="curGroup" value="${game?.team1?.groupName == game?.team2?.groupName ? game?.team1?.groupName : null}" />
					<g:if test="${curGroup}">
						<td><g:link action="list" params="[group: curGroup]">${curGroup}</g:link></td>
					</g:if>
					<g:else>
						<td><g:message code="ko.phase.${game?.phase}"/></td>
					</g:else>
				</g:if>
				<td style="text-align: right;">${game.team1.code ?: game.team1.name} <bg:flag team="${game.team1}" /></td>
				<td style="text-align: center;">${game.finalScore1}:${game.finalScore2}</td>
				<td><bg:flag team="${game.team2}" /> ${game.team2.code ?: game.team2.name}</td>
			</tr>
		</g:each>
		</tbody>
	</table>
</section>
<g:if test="${group}">
<g:link controller="game" action="list">
	<button type="button" class="btn btn-default">
		<span class="glyphicon glyphicon-list"></span> Alle Gruppen
	</button>
</g:link>
</g:if>

</body>

</html>
