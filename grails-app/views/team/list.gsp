<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<title><g:message code="teams.label" /></title>
	
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
</head>

<body>

<section id="list-teams" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
				<th>${message(code: 'teams.code.label', default: 'Code')}</th>
				<th>${message(code: 'teams.title.label', default: 'Title')}</th>
				<th>${message(code: 'groups.label', default: 'Group')}</th>
			</tr>
		</thead>
		<tbody>
		<g:each in="${teamsInstanceList}" status="i" var="teamsInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td><bg:flag team="${teamsInstance}" /> <g:link action="show" id="${teamsInstance.id}">${fieldValue(bean: teamsInstance, field: "code")}</g:link></td>
				<td>${fieldValue(bean: teamsInstance, field: "name")}</td>
				<td><g:link controller="game" action="list" params="[group:groupMap[teamsInstance.code]]" >${groupMap[teamsInstance.code]}</g:link></td>
			</tr>
		</g:each>
		</tbody>
	</table>
</section>

</body>

</html>
