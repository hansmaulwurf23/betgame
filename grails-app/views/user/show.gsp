<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<title>User</title>
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
</head>

<body>

<div class="well">${user.givenname} ${user.surname} (${betPerc.round(1)}% Spiele getippt)</div>

<section id="list-ranking" class="first">

	<g:render template="/game/wiloQuote" model="[distrData:scoreDistr, titel:'Punkte']"></g:render>
	
	<g:render template="barPlot" model="[barData:barData, titel:'Historie']"></g:render>
	
	
	<div class="panel panel-default">
	<div class="panel-heading">
		<div class="row">
			<div class="col-xs-7 text-center">
				
			</div>
			<div class="col-xs-4 text-center">
				${message(code: 'bet', default: 'Tipp')}
			</div>
		</div>
	</div>
	<g:each in="${bets}" var="betInstance">
	<div class="row condensed panel-body ${betInstance.user==currentUser?' current-user':''}">
		<div class="col-xs-3 text-right">
			<bg:flag team="${betInstance.game.team1}" /> ${betInstance.game.team1.code}
		</div>
		<div class="col-xs-1 text-center">${betInstance.game.score1}:${betInstance.game.score2}</div>
		<div class="col-xs-3 text-left">
			${betInstance.game.team2.code} <bg:flag team="${betInstance.game.team2}" />
		</div>
		<div class="col-xs-4 text-center">
			${betInstance.score1}:${betInstance.score2} (<bg:score bet="${betInstance}"/>) <span style="display:inline-block; min-width:15px; background-color:${['#f0ad4e', '#d9534f', '#5cb85c', '#428bca'].get(betInstance.getScore())}">&nbsp;</span>
		</div>
	</div>
	</g:each>
	<div class="panel-footer">
	<div class="row">
		<div class="col-xs-12"><small>Punkteschnitt: ${bets ? (bets*.getScore().sum().toDouble() / bets.size()).round(2) : '-'}</small></div>
	</div>
	</div>
</div>
	
</section>

</body>

</html>
