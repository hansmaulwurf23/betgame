
<%@ page import="de.betgame.sportdb.Grounds" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'grounds.label', default: 'Grounds')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
	
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
	<g:set var="citiesInstance" value="${groundsInstance.city}" />"
</head>

<body>

<section id="show-grounds" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="grounds.title.label" default="Title" /></td>
				<td valign="top" class="value">${fieldValue(bean: groundsInstance, field: "title")}</td>
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="grounds.capacity.label" default="Capacity" /></td>
				<td valign="top" class="value">${fieldValue(bean: groundsInstance, field: "capacity")}</td>
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="grounds.synonyms.label" default="Synonyms" /></td>
				<td valign="top" class="value">${fieldValue(bean: groundsInstance, field: "synonyms")}</td>
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="cities.name.label" default="City" /></td>
				<td valign="top" class="value">${fieldValue(bean: citiesInstance, field: "name")}</td>
			</tr>
			
			<tr class="prop">
				<td valign="top" class="name"><g:message code="cities.pop.label" default="Pop" /></td>
				<td valign="top" class="value">${fieldValue(bean: citiesInstance, field: "pop")}</td>
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="cities.popm.label" default="Popm" /></td>
				<td valign="top" class="value">${fieldValue(bean: citiesInstance, field: "popm")}</td>
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="grounds.timezone.label" default="Timezone" /></td>
				<td valign="top" class="value">${fieldValue(bean: groundsInstance, field: "timezone")}</td>
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
