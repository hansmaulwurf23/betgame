
<%@ page import="de.betgame.Bet" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<title><g:message code="bets.label"  /></title>
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
</head>

<body>

<section id="list-bet" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
				<th><i class="glyphicon glyphicon-pencil"></i></th>
				<th></th>
				<th>${message(code: 'bet.label', default: 'Bet')}</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<g:each in="${betInstanceList}" status="i" var="betInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td><g:link action="show" id="${betInstance.id}"><i class="glyphicon glyphicon-search"></i></g:link></td>
				<td><bg:score bet="${betInstance}"/></td>
				<td style="text-align: center;"><g:render template="betRow" model="[betInstance:betInstance, games:games]" /></td>
				<td>${betInstance.game?.score1}:${betInstance.game?.score2}</td>
				<td>
					<g:if test="${!betInstance.game.sameAsFinalScore}">
						(${betInstance.game?.finalScore1}:${betInstance.game?.finalScore2})
					</g:if>
				</td>
			</tr>
		</g:each>
		</tbody>
	</table>
</section>

</body>

</html>
