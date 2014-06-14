<div class="row">
	<div class="col-xs-4 text-right">
		<g:link controller="team" action="show" id="${gameInstance?.team1?.id}">
			${gameInstance?.team1?.name?.encodeAsHTML()}
		</g:link>
		<bg:flag net="${gameInstance.team1.net}" />
	</div>
	<div class="col-xs-4 text-center">
		${gameInstance.score1} : ${gameInstance.score2}
	</div>
	<div class="col-xs-4">
		<bg:flag net="${gameInstance.team2.net}" />
		<g:link controller="team" action="show" id="${gameInstance?.team2?.id}">
			${gameInstance?.team2?.name?.encodeAsHTML()}
		</g:link>
	</div>
</div>