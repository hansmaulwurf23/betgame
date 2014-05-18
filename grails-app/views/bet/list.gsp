
<%@ page import="de.betgame.Bet" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'bet.label', default: 'Bet')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>

<section id="list-bet" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<th><g:message code="bet.user.label" default="User" /></th>
			
				<g:sortableColumn property="gameid" title="${message(code: 'bet.gameid.label', default: 'Gameid')}" />
			
				<g:sortableColumn property="score1" title="${message(code: 'bet.score1.label', default: 'Score1')}" />
			
				<g:sortableColumn property="score2" title="${message(code: 'bet.score2.label', default: 'Score2')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${betInstanceList}" status="i" var="betInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${betInstance.id}">${fieldValue(bean: betInstance, field: "user")}</g:link></td>
			
				<td>${fieldValue(bean: betInstance, field: "gameid")}</td>
			
				<td>${fieldValue(bean: betInstance, field: "score1")}</td>
			
				<td>${fieldValue(bean: betInstance, field: "score2")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${betInstanceCount}" />
	</div>
</section>

</body>

</html>
