
<%@ page import="de.betgame.sportdb.Grounds" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'grounds.label', default: 'Grounds')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
	
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
</head>

<body>

<section id="list-grounds" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
				<th>${message(code: 'grounds.title.label', default: 'Title')}</th>
				<th>${message(code: 'grounds.city.label', default: 'City')}</th>
				<th>${message(code: 'grounds.capacity.label', default: 'Capacity')}</th>
				<th>${message(code: 'grounds.timezone.label', default: 'Timezone')}</th>
			</tr>
		</thead>
		<tbody>
		<g:each in="${groundsInstanceList}" status="i" var="groundsInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${groundsInstance.id}">${fieldValue(bean: groundsInstance, field: "title")}</g:link></td>
				<td>${groundsInstance.city?.name}</td>
				<td>${fieldValue(bean: groundsInstance, field: "capacity")}</td>
				<td>${fieldValue(bean: groundsInstance, field: "timezone")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div>
		<bs:paginate total="${groundsInstanceCount}" />
	</div>
</section>

</body>

</html>
