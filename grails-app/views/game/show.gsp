<%@ page import="de.betgame.Game" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'games.label', default: 'Games')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<div class="btn-toolbar my-1">
	<g:link action="list" class="btn btn-secondary ${ params.action == "list" ? 'active' : '' }">
		<i class="fas fa-list"></i> <g:message code="list.label"/></g:link>

</div>

<section id="show-games" class="first">
<div class="container">

<g:render template="/game/full" model="[gameInstance:gameInstance, myBet:myBet]"></g:render>
<bg:gameBets game="${gameInstance}" />

</div>
</section>

</body>

</html>
