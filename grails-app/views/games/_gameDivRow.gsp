<div class="row">
	<div class="col-xs-5 text-right">
		<g:link controller="teams" action="show" id="${gamesInstance?.team1?.id}">
			${gamesInstance?.team1?.title?.encodeAsHTML()}
		</g:link>
		<bg:flag country="${gamesInstance.team1.country}" />
	</div>
	<div class="col-xs-2 text-center">
		${gamesInstance.score1} : ${gamesInstance.score2}
	</div>
	<div class="col-xs-5">
		<bg:flag country="${gamesInstance.team2.country}" />
		<g:link controller="teams" action="show" id="${gamesInstance?.team2?.id}">
			${gamesInstance?.team2?.title?.encodeAsHTML()}
		</g:link>
	</div>
</div>