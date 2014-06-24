<div class="panel panel-default">
	<div class="panel-heading">
		<div class="row">
			<div class="col-xs-4 text-right">
				${message(code: 'name', default: 'Name')}
			</div>
			<div class="col-xs-4 text-center">
				${message(code: 'bet', default: 'Tipp')}
			</div>
			<div class="col-xs-4 text-left">
				${message(code: 'points', default: 'Punkte')}
			</div>
		</div>
	</div>
	<g:each in="${betInstances}" var="betInstance">
	<div class="row condensed panel-body ${betInstance.user==currentUser?' current-user':''}">
		<div class="col-xs-4 text-right">
			<g:link controller="user" action="show" id="${betInstance.user.id}">${betInstance.user.display}</g:link>
		</div>
		<div class="col-xs-4 text-center">
			${showBets?betInstance.score1:'*'}:${showBets?betInstance.score2:'*'}
		</div>
		<div class="col-xs-4">
			<g:if test="${!gameInstance.matchIsFinished}">(</g:if><bg:score bet="${betInstance}"/><g:if test="${!gameInstance.matchIsFinished}">)</g:if>
		</div>
	</div>
	</g:each>
	<div class="panel-footer">
	<div class="row">
		<div class="col-xs-12"><small>
			<g:message code="bet.totalCount" default="Tipp Anzahl" />: <g:formatNumber number="${stats.totalCount}" format="##" />
			&middot;
			&Oslash; <g:message code="points" default="Punkte" />: <g:if test="${!gameInstance.matchIsFinished}">(</g:if><g:formatNumber number="${stats.averagePoints}" format="#.##" /><g:if test="${!gameInstance.matchIsFinished}">)</g:if>
			</small></div>
	</div>
	</div>
</div>
