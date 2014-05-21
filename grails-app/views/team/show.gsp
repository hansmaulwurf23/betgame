
<%@ page import="de.betgame.sportdb.Teams" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'teams.label', default: 'Teams')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
	
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
</head>

<body>


<ul id="Menu" class="nav nav-pills margin-top-small">
	<li class="${ params.action == "list" ? 'active' : '' }">
		<g:link action="list"><i class="glyphicon glyphicon-th-list"></i> <g:message code="default.list.label" args="[entityName]"/></g:link>
	</li>
</ul>

<section id="show-teams" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="teams.title.label" default="Title" /></td>
				<td valign="top" class="value">${fieldValue(bean: teamInstance, field: "name")} <bg:flag net="${teamInstance.net}" /></td>
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="teams.code.label" default="Code" /></td>
				<td valign="top" class="value">${fieldValue(bean: teamInstance, field: "code")}</td>
			</tr>
			
		</tbody>
	</table>
</section>

</body>

</html>
