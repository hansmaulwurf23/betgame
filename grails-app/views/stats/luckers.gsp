<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<title>Luckers</title>
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
</head>

<body>

<g:render template="statsmenu"></g:render>

<section id="list-ranking" class="first">

	<div class="well"><g:message code="luckers.info" default="Anzahl richtig getippter Spielergebnisse"/></div>

	<table class="table table-sm table-striped bg-white mt-2">
		<thead class="thead-dark">
			<tr>
				<th>${message(code: 'name', default: 'Name')}</th>
				<th>${message(code: 'games', default: 'Spiele')}</th>
			</tr>
		</thead>
		<tbody>
		<g:each in="${luckyShots}" var="p" status="i">
			<tr class="${p.user_id == curUser.id ? 'bg-secondary text-white' : ''}">
				<td><g:link controller="user" action="show" id="${p.user_id}"
							class="${p.user_id == curUser.id ? 'text-white' : ''}">${p.display}</g:link></td>
				<td>${p.anz}</td>
			</tr>
		</g:each>
		</tbody>
	</table>
</section>

</body>

</html>
