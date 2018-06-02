<%@ page import="de.betgame.Game" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<title><g:message code="show.game" /></title>
</head>

<body>

<div class="btn-toolbar my-1">
	<g:link action="list" class="btn btn-secondary ${ params.action == "list" ? 'active' : '' }">
		<i class="fas fa-list"></i> <g:message code="list.label"/></g:link>

</div>

<section id="show-games" class="first">
<div class="container">

<g:render template="/game/full" model="[game:game, myBet:myBet]"></g:render>
<bg:gameBets game="${game}" />

</div>
</section>

</body>

</html>
