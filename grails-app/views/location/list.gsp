
<%@ page import="de.betgame.Location" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'location.label', default: 'Location')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
	
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
</head>

<body>

<section id="list-location" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
				<g:sortableColumn property="stadium" title="${message(code: 'location.stadium.label', default: 'Stadium')}" />
				<g:sortableColumn property="city" title="${message(code: 'location.city.label', default: 'City')}" />
				<g:sortableColumn property="capacity" title="${message(code: 'location.capacity.label', default: 'Capacity')}" />
			</tr>
		</thead>
		<tbody>
		<g:each in="${locationInstanceList}" status="i" var="locationInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td><g:link action="show" id="${locationInstance.id}">${fieldValue(bean: locationInstance, field: "stadium")}</g:link></td>
				<td>${fieldValue(bean: locationInstance, field: "city")}</td>
				<td>${fieldValue(bean: locationInstance, field: "capacity")}</td>
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${locationInstanceCount}" />
	</div>
</section>

</body>

</html>
