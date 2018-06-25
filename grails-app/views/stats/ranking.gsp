<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<title>Ranking</title>
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
</head>

<body>

<g:render template="statsmenu"></g:render>

<section id="list-ranking" class="first">

	<table class="table table-sm table-striped bg-white mt-2">
		<thead class="thead-dark">
			<tr>
				<th></th>
				<th>${message(code: 'name', default: 'Name')}</th>
				<th class="d-none d-md-table-cell">% (Tipps)</th>
				<th>E</th>
				<th>T</th>
				<th>S</th>
				<th colspan="2">${message(code: 'points', default: 'Punkte')}</th>
			</tr>
		</thead>
		<tbody>
		<g:each in="${punkte}" var="p">
			<tr class="${p.user_id == curUser.id ? 'bg-secondary text-white' : ''}">
				<td>${posMap[(p.punkte)]}</td>
				<td><g:link controller="user" action="show" id="${p.user_id}"
							class="${p.user_id == curUser.id ? 'text-white' : ''}">${p.display}</g:link></td>
				<td class="d-none d-md-table-cell">${betStats[(p.user_id)]?.percentage} (${betStats[(p.user_id)]?.betCount})</td>
				<td>${betStats[(p.user_id)]?.E ?: 0}</td>
				<td>${betStats[(p.user_id)]?.T ?: 0}</td>
				<td>${betStats[(p.user_id)]?.S ?: 0}</td>
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
