
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

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="bet.gameid.label" default="Gameid" /></td>
				<td valign="top" class="value">${fieldValue(bean: betInstance, field: "gameid")}</td>
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="bet.score1.label" default="Score1" /></td>
				<td valign="top" class="value">${fieldValue(bean: betInstance, field: "score1")}</td>
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="bet.score2.label" default="Score2" /></td>
				<td valign="top" class="value">${fieldValue(bean: betInstance, field: "score2")}</td>
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
