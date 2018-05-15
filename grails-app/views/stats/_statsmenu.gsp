<div class="btn-group my-2" role="group">
	<g:link class="btn btn-secondary ${params.action == "ranking"?'active':''}" action="ranking">
		<i class="fas fa-list"></i> <g:message code="table.label" default="Tabelle" />
	</g:link>

	<li class="btn-group" role="group">
	    <a class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" href="#">
	      <i class="fas fa-list-alt"></i> Alt <span class="caret"></span>
	    </a>
	    <div class="dropdown-menu ${ params.action in ['kicktippRanking', 'kickerRanking', 'rawRanking'] ? 'active' : '' }">
			<g:link class="dropdown-item ${params.action=="kicktippRanking"?'active':''}" action="kicktippRanking">
				<asset:image src="kicktipp.ico"/> (3, 2, 2) KickTipp
			</g:link>
			<g:link class="dropdown-item ${params.action=="kickerRanking"?'active':''}" action="kickerRanking">
				<asset:image src="kicker.ico" width="16" height="16" /> (3, 1, 1) Kicker
			</g:link>
			<g:link class="dropdown-item ${params.action=="rawRanking"?'active':''}" action="rawRanking">
				<i class="far fa-check-circle"></i> (1, 1, 1) Pur
			</g:link>
	    </div>
	</li>
	
	
	<g:link class="btn btn-secondary ${params.action == "luckers"?'active':''}" action="luckers">
		<i class="far fa-thumbs-up"></i> Luckers
	</g:link>
	<g:link class="btn btn-secondary ${params.action == "scores"?'active':''}" action="scores">
		<i class="fas fa-th"></i> Scores
	</g:link>
</div>
