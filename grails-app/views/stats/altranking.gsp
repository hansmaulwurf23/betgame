<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<title>${actionName.capitalize()}</title>
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
</head>

<body>

<g:render template="statsmenu"></g:render>

<section id="list-ranking" class="first">

	<div class="well">Ergebnis: ${wertung.E} | Tordifferenz: ${wertung.T} | Tendenz: ${wertung.S}</div>

	<table class="table table-sm table-striped mt-2">
		<thead class="thead-dark">
			<tr>
				<th></th>
				<th>${message(code: 'name', default: 'Name')}</th>
				<th colspan="2">${message(code: 'points', default: 'Punkte')}</th>
			</tr>
		</thead>
		<tbody>
		<g:each in="${punkte}" var="p">
			<tr class="${(posMap*.key.indexOf(p.punkte) % 2) == 0 ? '' : 'active'}">
				<td>${posMap[(p.punkte)]}</td>
				<td><g:link controller="user" action="show" id="${p.user_id}">${p.display}</g:link></td>
				<td>${p.punkte}</td>
				<td class="text-right"><bg:posChangeIcon posChange="${posChangeMap[(p.user_id)]}" /></td>
			</tr>
		</g:each>
		</tbody>
	</table>


	<g:render template="posChangeLegend"/>
</section>

</body>

</html>
