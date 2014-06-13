<html>

<head>
	<title><g:message code="default.welcome.title" args="[meta(name:'app.name')]"/> </title>
	<meta name="layout" content="kickstart" />
</head>

<body>

	<section id="intro" class="first">
		<h2>Welcome to Betgame</h2>
		<g:if test="${nextGames}">
		<h3><g:message code="next.game" default="Next Games"/> (<g:formatDate type="date" date="${nextGames[0].playAt}" />)</h3>
		<g:each in="${nextGames}" var="nextGame">
		<div class="panel panel-default">
			<div class="panel-heading">
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
			</div>
			<div class="panel-body">
			<g:if test="${myBets && myBets[nextGame]}">
				<g:set var="myBet" value="${myBets[nextGame][0]}" />
				<div class="col-xs-12 text-center"><g:link controller="bet" action='edit' id="${myBet.id}">${myBet.score1} : ${myBet.score2}</g:link></div>
			</g:if>
			<g:else>
				<div class="col-xs-12 text-center">
				<sec:ifNotLoggedIn>
					<g:link controller='login' action='auth'><g:message code="not.logged.in" default="Not Logged In" /></g:link>
				</sec:ifNotLoggedIn>
				<sec:ifLoggedIn>
					<g:link controller="bet" action='create' params="['game.id': nextGame.id]" ><g:message code="no.bet" default="No bet yet" /></g:link>
				</sec:ifLoggedIn>
				</div>
			</g:else>
			</div>
			<div class="panel-footer">
			<div class="row">
				<div class="col-xs-12"><small>Begin: <g:formatDate type="datetime" date="${nextGame?.playAt}" /></small></div>
			</div>
			</div>
		</div>
		</g:each>
		</g:if>
		
		<div class="row">
			<div class="col-xs-5">
			Bets: ${countBets}
			</div>
		</div>
		<div class="row">
			<div class="col-xs-5">
			Players: ${betters}
			</div>
		</div>
		<div class="row">
			<div class="col-xs-5">
			Visitors: ${userCount}
			</div>
		</div>
	</section>


</body>

</html>
