<div class="panel panel-default">
	<div class="panel-heading">
		<div class="row">
			<div class="col-xs-2">
				<g:link controller="game" action="show" id="${gameInstance?.id}">
  					<span class="glyphicon glyphicon-th-large"></span>
  				</g:link>
			</div>
			<div class="col-xs-2 text-right">
				<g:link controller="team" action="show" id="${gameInstance?.team1?.id}">
					${gameInstance?.team1?.code}
				</g:link>
			</div>
			<div class="col-xs-4 text-center">
				<bg:flag net="${gameInstance.team1.net}" />
				${gameInstance.score1}:${gameInstance.score2}
				<bg:flag net="${gameInstance.team2.net}" />
			</div>
			<div class="col-xs-4">
				<g:link controller="team" action="show" id="${gameInstance?.team2?.id}">
					${gameInstance?.team2?.code}
				</g:link>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<g:if test="${myBets && myBets[gameInstance]}">
			<g:set var="myBet" value="${myBets[gameInstance][0]}" />
			<div class="col-xs-12 text-center">
				<g:link controller="bet" action='edit' id="${myBet.id}">
					${myBet.score1}:${myBet.score2}
				</g:link>
			</div>
		</g:if>
		<g:else>
			<div class="col-xs-12 text-center">
				<sec:ifNotLoggedIn>
					<g:link controller='login' action='auth'>
						<g:message code="not.logged.in" default="Nicht angemeldet" />
					</g:link>
				</sec:ifNotLoggedIn>
				<sec:ifLoggedIn>
					<div class="alert alert-danger" style="margin-bottom:0px;">
					<g:link controller="bet" action='create' params="['game.id': gameInstance.id]" class="alert-link">
						<g:message code="no.bet" default="Noch kein Tipp abgegeben" />
					</g:link>
					</div>
				</sec:ifLoggedIn>
			</div>
		</g:else>
	</div>
	<div class="panel-footer">
		<div class="row">
			<div class="col-xs-5">
				<g:formatDate date="${gameInstance?.playAt}" formatName="default.gamedate.format" />
			</div>
			<div class="col-xs-2 text-center">
				<bg:gameStatus game="${gameInstance}" />
			</div>
			<div class="col-xs-5 text-right">
				<g:link controller="game" action="list" params="[group: gameInstance?.groupName]">Gruppe ${gameInstance?.groupName}
				</g:link>
			</div>
		</div>
	</div>
</div>