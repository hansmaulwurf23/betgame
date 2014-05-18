<%@page import="de.betgame.sportdb.Games"%>
<%@ page import="de.betgame.Bet" %>

	<g:hiddenField name="gameid" value="${betInstance.gameid}"/>

<div class="panel panel-default">
	
	<div class="panel-heading">
		<g:set var="gamesInstance" value="${Games.get(betInstance.gameid)}" />
		<g:render template="/games/gameDivRow" />
	</div>

	<div class="row panel-body">	
		<div class="col-xs-5">
			<g:field class="form-control" name="score1" type="number" value="${betInstance.score1}" required=""/>
			<span class="help-inline">${hasErrors(bean: betInstance, field: 'score1', 'error')}</span>
		</div>
		<div class="col-xs-2 text-center"> : </div>
		<div class="col-xs-5">
			<g:field class="form-control" name="score2" type="number" value="${betInstance.score2}" required=""/>
			<span class="help-inline">${hasErrors(bean: betInstance, field: 'score2', 'error')}</span>
		</div>
	</div>

</div>