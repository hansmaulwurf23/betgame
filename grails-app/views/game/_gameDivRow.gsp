<div class="row">
	<div class="col-xs-4 text-right">
		<g:link controller="team" action="show" id="${gameInstance?.team1?.id}">
			${gameInstance?.team1?.name?.encodeAsHTML()}
		</g:link>
	</div>
	<div class="col-xs-4 text-center">
		<bg:flag team="${gameInstance.team1}" /> ${gameInstance.score1}:${gameInstance.score2} <bg:flag team="${gameInstance.team2}" />
	</div>
	<div class="col-xs-4">
		<g:link controller="team" action="show" id="${gameInstance?.team2?.id}">
			${gameInstance?.team2?.name?.encodeAsHTML()}
		</g:link>
	</div>
</div>