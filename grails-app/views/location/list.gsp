<%@ page import="de.betgame.Location" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'location.label', default: 'Location')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>

<section id="list-location" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
				<th>${message(code: 'location.stadium.label')}</th>
				<th>${message(code: 'location.city.label')}</th>
				<th>${message(code: 'location.capacity.label')}</th>
			</tr>
		</thead>
		<tbody>
		<g:each in="${locations}" status="i" var="location">
			<tr>
				<td><g:link action="show" id="${location.id}">${fieldValue(bean: location, field: "stadium")}</g:link></td>
				<td>${fieldValue(bean: location, field: "city")}</td>
				<td>${fieldValue(bean: location, field: "capacity")}</td>
			</tr>
		</g:each>
		</tbody>
	</table>
</section>

</body>

</html>
