<%@ page import="de.betgame.Game" %>
<g:hiddenField name="game.id" value="${bet.game.id}"/>

<div class="card ">
	
	<div class="card-header">
		<g:set var="gameInstance" value="${de.betgame.Game.get(bet.game.id)}" />
		<g:render template="/game/gameDivRow" />
	</div>

	<div class="row card-body">
		<div class="col col-xs-5">
			<g:field class="form-control" name="score1" type="number" value="${bet.score1 ?: 0}" min="0" required=""/>
			<span class="help-inline">${hasErrors(bean: bet, field: 'score1', 'error')}</span>
		</div>
		<div class="col col-xs-2 text-center"> : </div>
		<div class="col col-xs-5">
			<g:field class="form-control" name="score2" type="number" value="${bet.score2 ?: 0}" min="0" required=""/>
			<span class="help-inline">${hasErrors(bean: bet, field: 'score2', 'error')}</span>
		</div>
	</div>

</div>