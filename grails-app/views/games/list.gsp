
<%@ page import="de.betgame.sportdb.Games" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'games.label', default: 'Games')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>
<ul id="Menu" class="nav nav-pills margin-top-small">
<g:each in="${groups}" var="g">
	<li class="${ g == group ? 'active' : '' }">
		<g:link action="list" params="[groupId:g.id]">${g.title[-1]}</g:link>
	</li>
</g:each>
</ul>

<section id="list-games" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
		</thead>
		<tbody>
		<g:each in="${gamesInstanceList}" status="i" var="gamesInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${gamesInstance.id}">
					<g:formatDate date="${gamesInstance.playAt}" type="datetime" timeStyle="SHORT" dateStyle="SHORT" />
				</g:link></td>
				<td style="text-align: right;">${gamesInstance.team1.code} <bg:flag country="${gamesInstance.team1.country}" /></td>
				<td style="text-align: center;">${gamesInstance.score1} : ${gamesInstance.score2}</td>
				<td><bg:flag country="${gamesInstance.team2.country}" /> ${gamesInstance.team2.code}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
</section>

</body>

</html>
