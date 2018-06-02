<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<title><g:message code="teams.label" /></title>
	
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
</head>

<body>

<section id="list-teams" class="first">

	<table class="table table-striped bg-white table-sm mt-2">
		<thead class="thead-dark">
			<tr>
				<th>${message(code: 'teams.code.label', default: 'Code')}</th>
				<th>${message(code: 'teams.title.label', default: 'Title')}</th>
				<th>${message(code: 'groups.label', default: 'Group')}</th>
			</tr>
		</thead>
		<tbody>
		<g:each in="${teams}" status="i" var="team">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td><bg:flag team="${team}" /> <g:link action="show" id="${team.id}">${fieldValue(bean: team, field: "code")}</g:link></td>
				<td>${fieldValue(bean: team, field: "name")}</td>
				<td><g:link controller="game" action="list" params="[group:team.groupName]" >${team.groupName}</g:link></td>
			</tr>
		</g:each>
		</tbody>
	</table>
</section>

</body>

</html>
