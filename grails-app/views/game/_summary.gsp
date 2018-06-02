<div class="card my-2 border-secondary">
	<div class="card-header">
		<div class="row">
			<div class="col col-xs-4 text-right">
				<g:link controller="game" action="show" id="${game?.id}">
					<bg:respTeam team="${game?.team1}"/>
				</g:link>
			</div>
			<div class="col col-xs-4 text-center">
				<bg:flag team="${game.team1}" />
				${game.finalScore1}:${game.finalScore2}
				<bg:flag team="${game.team2}" />
			</div>
			<div class="col col-xs-4">
				<g:link controller="game" action="show" id="${game?.id}">
					<bg:respTeam team="${game?.team2}"/>
				</g:link>
			</div>
		</div>
	</div>
	<div class="card-body">
		<g:if test="${myBets && myBets[game]}">
			<g:set var="myBet" value="${myBets[game][0]}" />
			<div class="col col-xs-12 text-center">
				<g:if test="${game.matchIsFinished}">
					<bg:score bet="${myBet}"/>
				</g:if>
				<g:link controller="bet" action='edit' id="${myBet.id}">
					${myBet.score1}:${myBet.score2}
				</g:link>
			</div>
		</g:if>
		<g:else>
			<div class="col col-xs-12 text-center">
				<sec:ifNotLoggedIn>
					<g:link controller='home' action='auth'>
						<g:message code="not.logged.in" default="Nicht angemeldet" />
					</g:link>
				</sec:ifNotLoggedIn>
				<sec:ifLoggedIn>
					<div class="alert alert-danger" style="margin-bottom:0px;">
					<g:link controller="bet" action='create' params="['game.id': game.id]" class="alert-link">
						<g:message code="no.bet" default="Noch kein Tipp abgegeben" />
					</g:link>
					</div>
				</sec:ifLoggedIn>
			</div>
		</g:else>
	</div>
	<div class="card-footer">
		<div class="row">
			<div class="col col-xs-5">
				<g:formatDate date="${game?.playAt}" formatName="default.gamedate.format" />
			</div>
			<div class="col col-xs-2 text-center">
				<bg:gameStatus game="${game}" />
			</div>
			<div class="col col-xs-5 text-right">
				<g:link controller="game" action="list" params="[phase: game?.phase]">${game?.phase}</g:link>
			</div>
		</div>
	</div>
</div>