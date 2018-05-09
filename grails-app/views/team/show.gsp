<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'teams.label', default: 'Teams')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>


<div class="btn-toolbar my-1">
	<g:link action="list" class="btn btn-secondary ${params.action == 'list' ? 'active' : ''}">
		<i class="fas fa-list"></i> <g:message code="list.label"/>
	</g:link>
</div>

<section id="show-teams" class="first">
<div class="container">
<div class="card ">
	
	<div class="card-header">
	<div class="row">
		<div class="col col-xs-6">${team.code}</div>
  		<div class="col col-xs-6 text-right">${team.name}</div>
  	</div>
	</div>
	
  	<g:each in="${games}" var="game">
  		<div class="row">
			<div class="col col-xs-3 text-right">
				<g:link controller="team" action="show" id="${game?.team1?.id}">
					${game?.team1?.code?.encodeAsHTML()}
				</g:link>
				<bg:flag team="${game.team1}" />
			</div>
			<div class="col col-xs-3 text-center">
				${game.score1}:${game.score2}
			</div>
			<div class="col col-xs-3">
				<bg:flag team="${game.team2}" />
				<g:link controller="team" action="show" id="${game?.team2?.id}">
					${game?.team2?.code?.encodeAsHTML()}
				</g:link>
			</div>
			<div class="col col-xs-3 text-center">
				<g:if test="${myBets[game]}">
					<bg:score bet="${myBets[game]}"></bg:score>
					${myBets[game].score1}:${myBets[game].score2}
				</g:if>
				<g:else>
					<sec:ifLoggedIn><g:link controller="bet" action="create" params="['game.id': game.id]" ><i class="glyphicon glyphicon-pencil"></i></g:link></sec:ifLoggedIn>
				</g:else>
			</div>
		</div>
  	</g:each>
	
	
</div>

</div>
	
</section>

</body>

</html>
