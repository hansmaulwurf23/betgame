
<%@ page import="de.betgame.Location" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'location.label', default: 'Location')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
	
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
</head>

<body>

<section id="show-location" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="location.stadium.label" default="Stadium" /></td>
				<td valign="top" class="value">${fieldValue(bean: locationInstance, field: "stadium")}</td>
			</tr>
			
			<tr class="prop">
				<td valign="top" class="name"><g:message code="location.city.label" default="City" /></td>
				<td valign="top" class="value">${fieldValue(bean: locationInstance, field: "city")}</td>
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="location.groundKey.label" default="Ground Key" /></td>
				<td valign="top" class="value">${fieldValue(bean: locationInstance, field: "groundKey")}</td>
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="location.capacity.label" default="Capacity" /></td>
				<td valign="top" class="value">${fieldValue(bean: locationInstance, field: "capacity")}</td>
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="location.dateCreated.label" default="Date Created" /></td>
				<td valign="top" class="value"><g:formatDate date="${locationInstance?.dateCreated}" /></td>
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="location.lastUpdated.label" default="Last Updated" /></td>
				<td valign="top" class="value"><g:formatDate date="${locationInstance?.lastUpdated}" /></td>
			</tr>
		
			
		
		</tbody>
	</table>
</section>

</body>

</html>
