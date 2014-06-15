
<%@ page import="de.betgame.Game" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>
<ul id="Menu" class="nav nav-pills margin-top-small">
<g:each in="${groups}" var="g">
	<li class="${ g == group ? 'active' : '' }">
		<g:link action="list" params="[group:g]">${g}</g:link>
	</li>
</g:each>
</ul>

<section id="list-game" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
		</thead>
		<tbody>
		<g:each in="${gameInstanceList}" status="i" var="gameInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${gameInstance.id}">
					<g:formatDate date="${gameInstance?.playAt}" formatName="default.gamedate.format" />
				</g:link></td>
				<g:if test="${group=='all'}"><td><g:link action="list" params="[group: gameInstance?.groupName]">${gameInstance?.groupName}</g:link></td></g:if>
				<td style="text-align: right;">${gameInstance.team1.code} <bg:flag net="${gameInstance.team1.net}" /></td>
				<td style="text-align: center;">${gameInstance.score1} : ${gameInstance.score2}</td>
				<td><bg:flag net="${gameInstance.team2.net}" /> ${gameInstance.team2.code}</td>
			</tr>
		</g:each>
		</tbody>
	</table>
</section>
<g:if test="${group!='all'}">
<g:link controller="game" action="list" params="[group:'all']">
	<button type="button" class="btn btn-default">
		<span class="glyphicon glyphicon-list"></span> Alle Gruppen
	</button>
</g:link>
</g:if>

</body>

</html>
