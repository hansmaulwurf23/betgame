<ul id="Menu" class="nav nav-pills small-pills margin-top-small" style="margin: 10px 0">
	<li class="${ params.action == "ranking" ? 'active' : '' }">
		<g:link action="ranking"><i class="glyphicon glyphicon-list"></i> <g:message code="table.label" default="Tabelle" /></g:link>
	</li>
	
	<li class="dropdown">
	    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
	      <i class="glyphicon glyphicon-list-alt"></i> Alt <span class="caret"></span>
	    </a>
	    <ul class="dropdown-menu ${ params.action in ['kicktippRanking', 'kickerRanking', 'rawRanking'] ? 'active' : '' }"">
	      	<li class="${ params.action == "kicktippRanking" ? 'active' : '' }">
				<g:link action="kicktippRanking"><asset:image src="kicktipp.ico"/> (3, 2, 2) KickTipp</g:link>
			</li>
			<li class="${ params.action == "kickerRanking" ? 'active' : '' }">
				<g:link action="kickerRanking"><asset:image src="kicker.ico" width="16" height="16" /> (3, 1, 1) Kicker </g:link>
			</li>
			<li class="${ params.action == "rawRanking" ? 'active' : '' }">
				<g:link action="rawRanking"><i class="glyphicon glyphicon-ok-circle"></i> (1, 1, 1) Pur</g:link>
			</li>
	    </ul>
	</li>
	
	
	<li class="${ params.action == "luckers" ? 'active' : '' }">
		<g:link action="luckers"><i class="glyphicon glyphicon-thumbs-up"></i> Luckers</g:link>
	</li>
	<li class="${ params.action == "scores" ? 'active' : '' }">
		<g:link action="scores"><i class="glyphicon glyphicon-th"></i> Scores</g:link>
	</li>
</ul>
