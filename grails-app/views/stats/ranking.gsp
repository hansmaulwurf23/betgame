<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<title>Ranking</title>
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
</head>

<body>
<section id="list-ranking" class="first">

	<table class="table margin-top-medium">
		<thead>
			<tr>
				<th>${message(code: 'rank', default: 'Rank')}</th>
				<th>${message(code: 'name', default: 'Name')}</th>
				<th>${message(code: 'points', default: 'Points')}</th>
			</tr>
		</thead>
		<tbody>
		<g:each in="${punkte}" status="i" var="p">
			<tr class="${(posMap[(p.punkte)] % 2) == 0 ? 'active' : ''}">
				<td>${posMap[(p.punkte)]}</td>
				<td>${p.givenname}</td>
				<td>${p.punkte}</td>
			</tr>
		</g:each>
		</tbody>
	</table>
</section>

</body>

</html>
