<html>

<head>
	<title><g:message code="default.welcome.title" args="[meta(name:'app.name')]"/> </title>
	<meta name="layout" content="kickstart" />
</head>

<body>

	<section id="intro" class="first">
		<h2>Welcome to Betgame <g:meta name="app.version"/></h2>
		<g:if test="${nextGame}">
		<h3><g:message code="next.game" default="next Game"/></h3>
		<div class="row">
			<div class="col-xs-5 text-right">
				<g:link controller="team" action="show" id="${nextGame?.team1?.id}">
					${nextGame?.team1?.name?.encodeAsHTML()}
				</g:link>
				<bg:flag net="${nextGame.team1.net}" />
			</div>
			<div class="col-xs-2 text-center">
				${nextGame.score1} : ${nextGame.score2}
			</div>
			<div class="col-xs-5">
				<bg:flag net="${nextGame.team2.net}" />
				<g:link controller="team" action="show" id="${nextGame?.team2?.id}">
					${nextGame?.team2?.name?.encodeAsHTML()}
				</g:link>
			</div>
		</div>
		<g:if test="${myBet}">
			<div class="col-xs-12 text-center">${myBet.score1} : ${myBet.score2}</div>
		</g:if>
		<g:else>
			<div class="col-xs-12 text-center">
			<sec:ifNotLoggedIn>
				<g:link controller='login' action='auth'><g:message code="not.logged.in" default="Not Logged In" /></g:link>
			</sec:ifNotLoggedIn>
			<sec:ifLoggedIn>
				<g:link controller="bet" action='create' params="['game.id': gameInstance.id]" ><g:message code="no.bet" default="No bet yet" /></g:link>
			</sec:ifLoggedIn>
			</div>
		</g:else>
		</g:if>
	</section>


</body>

</html>
