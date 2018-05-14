<div class="row">
	<div class="col col-xs-4 text-right">
		<g:link controller="team" action="show" id="${game?.team1?.id}">
			${game?.team1?.name?.encodeAsHTML()}
		</g:link>
	</div>
	<div class="col col-xs-4 text-center">
		<bg:flag team="${game.team1}" /> ${game.finalScore1}:${game.finalScore2} <bg:flag team="${game.team2}" />
	</div>
	<div class="col col-xs-4">
		<g:link controller="team" action="show" id="${game?.team2?.id}">
			${game?.team2?.name?.encodeAsHTML()}
		</g:link>
	</div>
</div>