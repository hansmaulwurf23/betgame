<html>

<head>
	<title>Betgame</title>
	<meta name="layout" content="main" />
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('.').on('show.bs.collapse', function () {
		         $(this).addClass('active');
		    });

		    $('.').on('hide.bs.collapse', function () {
		         $(this).removeClass('active');
		    });
		});
	</script>
</head>

<body>

	<section id="intro" class="first">
		<h2 class="mt-2">WM 2018</h2>
			<div class="card ">
				<g:if test="${nextGames}">
					<div class="card-header">
						<h4 class="card-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseNextGames">
                                <g:message code="next.games" default="Nächste Spiele" /> (<g:formatDate format="EE dd.MM." date="${nextGames[0].playAt}" /> - <g:formatDate format="EE dd.MM." date="${nextGames[-1].playAt}" />)
							</a>
						</h4>
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
						<h4 class="card-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseLastGames">
                                <g:message code="last.games" default="Letzte Spiele" /> (<g:formatDate format="EE dd.MM." date="${lastGames[0].playAt}" /> - <g:formatDate format="EE dd.MM." date="${lastGames[-1].playAt}" />)
							</a>
						</h4>
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
