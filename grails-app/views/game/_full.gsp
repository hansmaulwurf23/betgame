<div class="card border-secondary">

	<div class="card-header">
		<div class="row">
			<div class="col col-xs-4">
				<g:formatDate date="${game?.playAt}" formatName="default.gamedate.format" />
			</div>
			<div class="col col-xs-4 text-center">
				<bg:gameStatus game="${game}" />
			</div>
			<div class="col col-xs-4 text-right">
				<g:link controller="game" action="list" params="[phase: game?.phase]">${game?.phase}</g:link>
			</div>
		</div>
	</div>

	<div class="card-body">
	<div class="row">
		<div class="col col-xs-12 text-center">
			@
			<g:link controller="location" action="show" id="${game?.location?.id}">
				${game?.location?.encodeAsHTML()}
			</g:link>
		</div>
	</div>

	<g:render template="gameDivRow" />
	
	<g:if test="${!game.sameAsFinalScore}" >
		<div class="col col-xs-12 text-center">
			(${game?.score1}:${game?.score2})
		</div>
	</g:if>

	<g:if test="${game.goalInfos()}">
		<hr />
		<g:each in="${game.goalInfos()}" status="i" var="g">
		<div class="row">
			<div class="col col-xs-10">
				<span class="badge">${g.minute ?: '?'}.</span> ${g.score1}:${g.score2} ${g.getter}
			</div>
			<div class="col col-xs-2 text-right">
				<bg:goalInfos goal="${g}" />
			</div>
		</div>
		</g:each>
		<g:if test="${game.numberOfViewers}">
		<div class="row" style="margin-top:15px;">
			<div class="col col-xs-12">
				<g:message code="spectators" default="Zuschauer"/>: ${game.numberOfViewers}
			</div>
		</div>
		</g:if>
		<hr />
	</g:if>

	<div class="row">
		<g:if test="${myBet}">
			<div class="col col-xs-12 text-center">
				<g:link controller="bet" action='edit' id="${myBet.id}">
					${myBet.score1}:${myBet.score2}
				</g:link>
			</div>
		</g:if>
		<g:else>
			<div class="col col-xs-12 text-center">
				<sec:ifNotLoggedIn>
					<g:link controller='login' action='auth'>
						<g:message code="not.logged.in" default="Nicht angemeldet" />
					</g:link>
				</sec:ifNotLoggedIn>
				<sec:ifLoggedIn>
					<g:link controller="bet" action='create' params="['game.id': game.id]">
						<g:message code="no.bet" default="Noch kein Tipp abgegeben" />
					</g:link>
				</sec:ifLoggedIn>
			</div>
		</g:else>
	</div>
	</div>

	<div class="card-footer">
		<div class="row">
			<div class="col col-xs-12">
				<small>updated: <g:formatDate date="${game?.lastUpdated}" formatName="default.gamedate.format" /></small> 
				<sec:ifAnyGranted roles="ROLE_IDMADMIN, ROLE_MAILADMIN">
					<g:link class="btn btn-sm btn-secondary" action="edit" id="${game.id}">Edit Score</g:link>
				</sec:ifAnyGranted>
				<sec:ifAnyGranted roles="ROLE_MAILADMIN">
					<g:link class="btn btn-sm btn-secondary" action="forceFetch" id="${game.id}">Fetch</g:link>
				</sec:ifAnyGranted>
			</div>
		</div>
	</div>
</div>