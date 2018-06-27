<html>

<head>
	<title>Betgame</title>
	<meta name="layout" content="main" />
</head>

<body>

	<section id="intro" class="first">
		<h2 class="mt-2">WM 2018</h2>
			<div class="card ">
				<g:if test="${nextGames}">
					<div class="card-header">
						<a data-toggle="collapse" data-parent="#accordion" href="#collapseNextGames">
							<b><g:message code="next.games" default="Nächste Spiele" /></b>
						</a>
					</div>
					<div id="collapseNextGames" class="card-collapse collapse in">
						<div class="card-body">
							<g:each in="${nextGames}" var="nextGame">
								<g:render template="/game/summary" model="[game:nextGame]"></g:render>
							</g:each>
						</div>
					</div>
				</g:if>
			</div>
			<div class="card ">
				<g:if test="${lastGames}">
					<div class="card-header">
						<a data-toggle="collapse" data-parent="#accordion" href="#collapseLastGames">
							<b><g:message code="last.games" default="Letzte Spiele" /></b>
						</a>
					</div>
					<div id="collapseLastGames" class="card-collapse collapse">
						<div class="card-body">
							<g:each in="${lastGames}" var="lastGame">
								<g:render template="/game/summary" model="[game:lastGame]"></g:render>
							</g:each>
						</div>
					</div>
				</g:if>
			</div>


		<div class="card">
			<div class="card-body">
			<g:message code="bets.placed" default="Tipps abegeben" />: ${countBets}
			<br/>
			<g:message code="players" default="Mitspieler" />: ${betters}
			<br/>
			<g:message code="visitors" default="Besucher" /> (SSO): ${userCount}
			</div>
		</div>
	</section>


</body>

</html>
