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

	<table class="table margin-top-medium">
		<thead>
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
	
	
	<div class="well"><i class="glyphicon glyphicon-circle-arrow-up text-success"></i>/<i class="glyphicon glyphicon-circle-arrow-down text-danger"></i> : Ver&auml;nderung zum Vortag
	</div>
</section>

</body>

</html>
