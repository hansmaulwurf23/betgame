<div class="card">

	<div class="card-header">
		<div class="row">
			<div class="col col-xs-4">
				<g:formatDate date="${gameInstance?.playAt}" formatName="default.gamedate.format" />
			</div>
			<div class="col col-xs-4 text-center">
				<bg:gameStatus game="${gameInstance}" />
			</div>
			<div class="col col-xs-4 text-right">
				<g:link controller="game" action="list" params="[phase: gameInstance?.phase]">${gameInstance?.phase}</g:link>
			</div>
		</div>
	</div>

	<div class="card-body">
	<div class="row">
		<div class="col col-xs-12 text-center">
			@
			<g:link controller="location" action="show" id="${gameInstance?.location?.id}">
				${gameInstance?.location?.encodeAsHTML()}
			</g:link>
		</div>
	</div>

	<g:render template="gameDivRow" />
	
	<g:if test="${!gameInstance.sameAsFinalScore}" >
		<div class="col col-xs-12 text-center">
			(${gameInstance?.score1}:${gameInstance?.score2})
		</div>
	</g:if>

	<g:if test="${gameInstance.goalInfos()}">
		<hr />
		<g:each in="${gameInstance.goalInfos()}" status="i" var="g">
		<div class="row">
			<div class="col col-xs-10">
				<span class="badge">${g.minute}.</span> ${g.score1}:${g.score2} ${g.getter}
			</div>
			<div class="col col-xs-2 text-right">
				<bg:goalInfos goal="${g}" />
			</div>
		</div>
		</g:each>
		<g:if test="${gameInstance.numberOfViewers}">
		<div class="row" style="margin-top:15px;">
			<div class="col col-xs-12">
				<g:message code="spectators" default="Zuschauer"/>: ${gameInstance.numberOfViewers}
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
					<g:link controller="bet" action='create' params="['game.id': gameInstance.id]">
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
				<small>updated: <g:formatDate date="${gameInstance?.lastUpdated}" formatName="default.gamedate.format" /></small> 
				<sec:ifAnyGranted roles="ROLE_MAILADMIN">
					<g:link action="edit" id="${gameInstance.id}">Edit Score</g:link>
					<g:link action="forceFetch" id="${gameInstance.id}">Fetch</g:link>
				</sec:ifAnyGranted>
			</div>
		</div>
	</div>
</div>