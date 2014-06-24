<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<title>User</title>
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
</head>

<body>

<div class="well">${user.givenname} ${user.surname} (${betPerc.round(1)}% Spiele getippt)</div>

<section id="list-ranking" class="first">

	<g:render template="/game/wiloQuote" model="[distrData:scoreDistr, titel:'Punkte']"></g:render>
	<g:render template="/game/wiloQuote" model="[distrData:gameDistr, titel:'Heim/Gast']"></g:render>
	
	<g:render template="barPlot" model="[barData:barData, titel:'Historie']"></g:render>
</section>

</body>

</html>
