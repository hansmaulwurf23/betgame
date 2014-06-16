<ul id="Menu" class="nav nav-pills margin-top-small">
	<li class="${ params.action == "ranking" ? 'active' : '' }">
		<g:link action="ranking"><i class="glyphicon glyphicon-list"></i> <g:message code="table.label" default="Tabelle" /></g:link>
	</li>
	<li class="${ params.action == "luckers" ? 'active' : '' }">
		<g:link action="luckers"><i class="glyphicon glyphicon-thumbs-up"></i> Luckers</g:link>
	</li>
	<li class="${ params.action == "scores" ? 'active' : '' }">
		<g:link action="scores"><i class="glyphicon glyphicon-thumbs-up"></i> Scores</g:link>
	</li>
</ul>
