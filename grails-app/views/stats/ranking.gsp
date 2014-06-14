<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<title>Ranking</title>
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
</head>

<body>

<g:render template="statsmenu"></g:render>

<section id="list-ranking" class="first">

	<table class="table margin-top-medium">
		<thead>
			<tr>
				<th>${message(code: 'rank', default: 'Rang')}</th>
				<th>${message(code: 'name', default: 'Name')}</th>
				<th>${message(code: 'points', default: 'Punkte')}</th>
			</tr>
		</thead>
		<tbody>
		<g:each in="${punkte}" var="p">
			<tr class="${(posMap*.key.indexOf(p.punkte) % 2) == 0 ? '' : 'active'}">
				<td>${posMap[(p.punkte)]}</td>
				<td>${nameMap[p]}</td>
				<td>${p.punkte}</td>
			</tr>
		</g:each>
		</tbody>
	</table>
</section>

</body>

</html>
