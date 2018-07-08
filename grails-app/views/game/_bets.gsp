<%@page import="java.lang.Math"%>
<div class="card my-1 border-secondary">
	<div class="card-header">
		<div class="row">
			<div class="col col-xs-4 text-right">
				${message(code: 'name', default: 'Name')}
			</div>
			<div class="col col-xs-4 text-center">
				${message(code: 'bet', default: 'Tipp')}
			</div>
			<div class="col col-xs-4 text-left">
				${message(code: 'points', default: 'Punkte')}
			</div>
		</div>
	</div>
    <div class="card-body">
    <g:each in="${betInstances}" var="betInstance">
	<div class="row condensed mt-1">
		<div class="col col-xs-4 text-right ">
			<g:link class="${betInstance.user == currentUser ? ' font-weight-bold font-italic':''}" controller="user" action="show" id="${betInstance.user.id}">${betInstance.user.display}</g:link>
		</div>
		<div class="col col-xs-4 text-center ${gameInstance.matchStarted ? 'goaldiff_' + (Math.signum(betInstance.goalDiff?.toDouble() ?: 0.0).toInteger() + 2).toString() : ''}">
			${showBets?betInstance.score1:'*'}:${showBets?betInstance.score2:'*'}
		</div>
		<div class="col col-xs-4">
			<g:if test="${gameInstance.matchStarted}"><bg:score bet="${betInstance}"/></g:if>
		</div>
	</div>
	</g:each>
    </div>
	<div class="card-footer">
	<div class="row">
		<div class="col col-xs-12"><small>
			<g:message code="bet.totalCount" default="Tipp Anzahl" />: <g:formatNumber number="${stats.totalCount}" format="##" />
			&middot;
			&Oslash; <g:message code="points" default="Punkte" />: <g:if test="${!gameInstance.matchIsFinished}">(</g:if><g:formatNumber number="${stats.averagePoints}" format="#.##" /><g:if test="${!gameInstance.matchIsFinished}">)</g:if>
			</small></div>
	</div>
	</div>
</div>
