
<%@ page import="de.betgame.sportdb.Games" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'games.label', default: 'Games')}" />
	<title><g:message code="default.index.label" args="[entityName]" /></title>
</head>

<body>

<section id="index-games" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="playAt" title="${message(code: 'games.playAt.label', default: 'Play At')}" />
			
				<th><g:message code="games.group.label" default="Group" /></th>
			
				<th><g:message code="games.ground.label" default="Ground" /></th>
			
				<th><g:message code="games.team1.label" default="Team1" /></th>
			
				<th><g:message code="games.team2.label" default="Team2" /></th>
			
				<g:sortableColumn property="key" title="${message(code: 'games.key.label', default: 'Key')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${gamesInstanceList}" status="i" var="gamesInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${gamesInstance.id}">${fieldValue(bean: gamesInstance, field: "playAt")}</g:link></td>
			
				<td>${fieldValue(bean: gamesInstance, field: "group")}</td>
			
				<td>${fieldValue(bean: gamesInstance, field: "ground")}</td>
			
				<td>${fieldValue(bean: gamesInstance, field: "team1")}</td>
			
				<td>${fieldValue(bean: gamesInstance, field: "team2")}</td>
			
				<td>${fieldValue(bean: gamesInstance, field: "key")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${gamesInstanceCount}" />
	</div>
</section>

</body>

</html>
