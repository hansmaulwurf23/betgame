<html>

<head>
	<title>Betgame</title>
	<meta name="layout" content="main" />
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('.panel-default').on('show.bs.collapse', function () {
		         $(this).addClass('active');
		    });

		    $('.panel-default').on('hide.bs.collapse', function () {
		         $(this).removeClass('active');
		    });
		});
	</script>
</head>

<body>

	<section id="intro" class="first">
		<h2>EM 2016</h2>
		<div class="panel-group" id="accordion">
			<div class="panel panel-default">
				<g:if test="${nextGames}">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseNextGames"> <g:message code="next.games" default="Nächste Spiele" /> (<g:formatDate format="EE dd.MM." date="${nextGames[0].playAt}" /> - <g:formatDate format="EE dd.MM." date="${nextGames[-1].playAt}" />)
							</a>
						</h4>
					</div>
					<div id="collapseNextGames" class="panel-collapse collapse in">
						<div class="panel-body">
							<g:each in="${nextGames}" var="nextGame">
								<g:render template="/game/summary" model="[gameInstance:nextGame]"></g:render>
							</g:each>
						</div>
					</div>
				</g:if>
			</div>
			<div class="panel panel-default">
				<g:if test="${lastGames}">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#collapseLastGames"> <g:message code="last.games" default="Letzte Spiele" /> (<g:formatDate format="EE dd.MM." date="${lastGames[0].playAt}" /> - <g:formatDate format="EE dd.MM." date="${lastGames[-1].playAt}" />)
							</a>
						</h4>
					</div>
					<div id="collapseLastGames" class="panel-collapse collapse">
						<div class="panel-body">
							<g:each in="${lastGames}" var="lastGame">
								<g:render template="/game/summary" model="[gameInstance:lastGame]"></g:render>
							</g:each>
						</div>
					</div>
				</g:if>
			</div>
		</div>
		<br />
		<div class="well">
				<g:message code="bets.placed" default="Tipps abegeben" />: ${countBets}
				<br/>
				<g:message code="players" default="Mitspieler" />: ${betters}
				<br/>
				<g:message code="visitors" default="Besucher" /> (SSO): ${userCount}
		</div>
	</section>


</body>

</html>
