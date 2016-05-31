
<%@ page import="de.betgame.Game" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<title><g:message code="games.label" /></title>
</head>

<body>
<ul id="Menu" class="nav nav-pills margin-top-small groups">
<g:each in="${groups}" var="g">
	<li class="${ g == group ? 'active' : '' }" style="margin: 5px 0px;">
		<g:link action="list" params="[group:g]">${g}</g:link>
	</li>
</g:each>
<li class="${ phase ? 'active' : '' }" style="margin: 5px 0px;">
	<g:link action="list" params="[phase:'KO']">K.O.</g:link>
</li>
</ul>

<section id="list-game" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
		</thead>
		<tbody>
		<g:each in="${gameInstanceList}" status="i" var="gameInstance">
			<tr class="${gameInstance.matchIsFinished ? 'active' : ''}">
			
				<td><g:link action="show" id="${gameInstance.id}">
					<g:formatDate date="${gameInstance?.playAt}" formatName="default.gamedate.format" />
				</g:link></td>
				<g:if test="${!group}">
				<g:if test="${!phase && gameInstance?.groupName}">
					<td><g:link action="list" params="[group: gameInstance?.groupName]">${gameInstance?.groupName}</g:link></td>
				</g:if>
				<g:else>
					<td><g:message code="ko.phase.${gameInstance?.phase}"/></td>
				</g:else>
				</g:if>
				<td style="text-align: right;">${gameInstance.team1.code} <bg:flag team="${gameInstance.team1}" /></td>
				<td style="text-align: center;">${gameInstance.score1} : ${gameInstance.score2}</td>
				<td><bg:flag team="${gameInstance.team2}" /> ${gameInstance.team2.code}</td>
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
