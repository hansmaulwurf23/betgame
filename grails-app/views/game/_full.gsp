<div class="panel panel-default">

	<div class="panel-heading">
		<div class="row">
			<div class="col-xs-4">
				<g:formatDate date="${gameInstance?.playAt}" formatName="default.gamedate.format" />
			</div>
			<div class="col-xs-4 text-center">
				<bg:gameStatus game="${gameInstance}" />
			</div>
			<div class="col-xs-4 text-right">
				<g:link action="list" params="[group: gameInstance?.groupName]">Gruppe ${gameInstance?.groupName}
				</g:link>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12 text-center">
			@
			<g:link controller="location" action="show" id="${gameInstance?.location?.id}">
				${gameInstance?.location?.encodeAsHTML()}
			</g:link>
		</div>
	</div>

	<g:render template="gameDivRow" />

	<g:if test="${gameInstance.goalInfos()}">
		<hr />
		<g:each in="${gameInstance.goalInfos()}" status="i" var="g">
		<div class="row">
			<div class="col-xs-10">
				<span class="badge">${g.minute}.</span> ${g.score1}:${g.score2} ${g.getter}
			</div>
			<div class="col-xs-2 text-right">
				<bg:goalInfos goal="${g}" />
			</div>
		</div>
		</g:each>
		<g:if test="${gameInstance.numberOfViewers}">
		<div class="row" style="margin-top:15px;">
			<div class="col-xs-12">
				<g:message code="spectators" default="Zuschauer"/>: ${gameInstance.numberOfViewers}
			</div>
		</div>
		</g:if>
		<hr />
	</g:if>

	<div class="row">
		<g:if test="${myBet}">
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
					<g:link controller="bet" action='create' params="['game.id': gameInstance.id]">
						<g:message code="no.bet" default="Noch kein Tipp abegeben" />
					</g:link>
				</sec:ifLoggedIn>
			</div>
		</g:else>
	</div>

	<div class="panel-footer">
		<div class="row">
			<div class="col-xs-12">
				<small>updated: <g:formatDate date="${gameInstance?.lastUpdated}" formatName="default.gamedate.format" /></small> 
				<sec:ifAnyGranted roles="ROLE_IDMADMIN"><g:link action="edit" id="${gameInstance.id}">Manually Edit Score</g:link></sec:ifAnyGranted>
			</div>
		</div>
	</div>
</div>