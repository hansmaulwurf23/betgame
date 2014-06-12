<%@page import="de.betgame.Game"%>
<%@ page import="de.betgame.Bet" %>

	<g:hiddenField name="game.id" value="${betInstance.game.id}"/>

<div class="panel panel-default">
	
	<div class="panel-heading">
		<g:set var="gameInstance" value="${Game.get(betInstance.game.id)}" />
		<g:render template="/game/gameDivRow" />
	</div>

	<div class="row panel-body">	
		<div class="col-xs-5">
			<g:field class="form-control" name="score1" type="number" value="${betInstance.score1 ?: 0}" min="0" required=""/>
			<span class="help-inline">${hasErrors(bean: betInstance, field: 'score1', 'error')}</span>
		</div>
		<div class="col-xs-2 text-center"> : </div>
		<div class="col-xs-5">
			<g:field class="form-control" name="score2" type="number" value="${betInstance.score2 ?: 0}" min="0" required=""/>
			<span class="help-inline">${hasErrors(bean: betInstance, field: 'score2', 'error')}</span>
		</div>
	</div>

</div>