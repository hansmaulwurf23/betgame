
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
		<g:link action="list" params="[groupId:g.id]"><i class="glyphicon glyphicon-list"></i> ${g.title[-1]}</g:link>
	</li>
</g:each>
</ul>

<section id="list-games" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="playAt" title="${message(code: 'games.playAt.label', default: 'Play At')}" />
				<th><g:message code="games.ground.label" default="Ground" /></th>
				<th><g:message code="games.team1.label" default="Team1" /></th>
				<th><g:message code="games.team2.label" default="Team2" /></th>
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${gamesInstanceList}" status="i" var="gamesInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${gamesInstance.id}">${fieldValue(bean: gamesInstance, field: "playAt")}</g:link></td>
				<td>${fieldValue(bean: gamesInstance, field: "ground")}</td>
				<td>${fieldValue(bean: gamesInstance, field: "team1")}</td>
				<td>${fieldValue(bean: gamesInstance, field: "team2")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
</section>

</body>

</html>
