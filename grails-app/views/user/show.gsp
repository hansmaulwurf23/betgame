<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<title>User</title>
</head>

<body>

<div class="well">${user.givenname} ${user.surname} (${betPerc?.round(1)}% Spiele getippt)</div>

<section id="list-ranking" class="first">

	<g:if test="${scoreDistr}">
		<g:render template="/game/wiloQuote" model="[distrData:scoreDistr, titel:'Punkte']"></g:render>
	</g:if>

	<g:if test="${barData}">
		<g:render template="barPlot" model="[barData:barData, titel:'Historie']"></g:render>
	</g:if>
	
	
	<div class="card ">
	<div class="card-header">
		<div class="row">
			<div class="col col-xs-7 text-center">
				
			</div>
			<div class="col col-xs-4 text-center">
				${message(code: 'bet', default: 'Tipp')}
			</div>
		</div>
	</div>
	<div class="card-body">

	<g:each in="${bets}" var="betInstance">
	<div class="row condensed ${betInstance.user==currentUser?' current-user':''}">
		<div class="col col-xs-3 text-right">
			${betInstance.game.team1.code} <bg:flag team="${betInstance.game.team1}" />
		</div>
		<div class="col col-xs-2 text-center">${betInstance.game.score1}:${betInstance.game.score2}</div>
		<div class="col col-xs-3 text-left">
			<bg:flag team="${betInstance.game.team2}" /> ${betInstance.game.team2.code}
		</div>
		<div class="col col-xs-3 text-center">
			${betInstance.score1}:${betInstance.score2} <bg:score bet="${betInstance}"/>
		</div>
	</div>
	</g:each>

	</div>
	<div class="card-footer">
	<div class="row">
		<div class="col col-xs-12"><small>Punkteschnitt: ${bets ? (bets*.getScore().sum().toDouble() / bets.size()).round(2) : '-'}</small></div>
	</div>
	</div>
</div>
	
</section>

</body>

</html>
