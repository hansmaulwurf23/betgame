
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
<g:each in="${groups}" var="g">
	<li class="nav-item">
		<g:link class="nav-link ${ g == group ? 'active' : '' }" action="list" params="[group:g]">${g}</g:link>
	</li>
</g:each>
<li class="nav-item">
	<g:link class="nav-link ${ phase ? 'active' : '' }" action="list" params="[phase:'KO']">K.O.</g:link>
</li>
</ul>

<section id="list-game" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
		</thead>
		<tbody>
		<g:each in="${gameInstanceList}" status="i" var="gameInstance">
			<tr class="${gameInstance.matchIsFinished ? 'active' : ''}">
				<g:set var="notbet" value="${!(gameInstance.id in gameIDsFromBets) }" />
				<td><g:link action="show" id="${gameInstance.id}" class="${notbet ? 'text-danger' : ''}">
					<g:formatDate date="${gameInstance?.playAt}" formatName="default.gamedate.format" />${notbet ? '!' : ''}
				</g:link></td>
				<g:if test="${!group}">
				<g:if test="${!phase && gameInstance?.groupName}">
					<td><g:link action="list" params="[group: gameInstance?.groupName]">${raw(NameUtil.convertGroupName(gameInstance.groupName))}</g:link></td>
				</g:if>
				<g:else>
					<td><g:message code="ko.phase.${gameInstance?.phase}"/></td>
				</g:else>
				</g:if>
				<td style="text-align: right;">${gameInstance.team1.code ?: gameInstance.team1.name} <bg:flag team="${gameInstance.team1}" /></td>
				<td style="text-align: center;">${gameInstance.finalScore1}:${gameInstance.finalScore2}</td>
				<td><bg:flag team="${gameInstance.team2}" /> ${gameInstance.team2.code ?: gameInstance.team2.name}</td>
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
