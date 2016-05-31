
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
				<th>${message(code: 'bet.label', default: 'Bet')}</th>
			</tr>
		</thead>
		<tbody>
		<g:each in="${betInstanceList}" status="i" var="betInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td><g:link action="show" id="${betInstance.id}"><i class="glyphicon glyphicon-search"></i></g:link></td>
				<td><g:render template="betRow" model="[betInstance:betInstance, games:games]" /></td>
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
