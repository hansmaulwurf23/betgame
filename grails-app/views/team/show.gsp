<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'teams.label', default: 'Teams')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
	
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
</head>

<body>


<ul id="Menu" class="nav nav-pills margin-top-small">
	<li class="${ params.action == "list" ? 'active' : '' }">
		<g:link action="list"><i class="glyphicon glyphicon-th-list"></i> <g:message code="default.list.label" args="[entityName]"/></g:link>
	</li>
</ul>

<section id="show-teams" class="first">
<div class="container">
<div class="panel panel-default">
	
	<div class="panel-heading">
	<div class="row">
		<div class="col-xs-6">${teamInstance.code}</div>
  		<div class="col-xs-6 text-right">${teamInstance.name}</div>
  	</div>
	</div>
	
  	<g:each in="${games}" var="gameInstance">
  		<div class="row">
			<div class="col-xs-4 text-right">
				<g:link controller="team" action="show" id="${gameInstance?.team1?.id}">
					${gameInstance?.team1?.code?.encodeAsHTML()}
				</g:link>
				<bg:flag net="${gameInstance.team1.net}" />
			</div>
			<div class="col-xs-2 text-center">
				${gameInstance.score1} : ${gameInstance.score2}
			</div>
			<div class="col-xs-4">
				<bg:flag net="${gameInstance.team2.net}" />
				<g:link controller="team" action="show" id="${gameInstance?.team2?.id}">
					${gameInstance?.team2?.code?.encodeAsHTML()}
				</g:link>
			</div>
			<div class="col-xs-2 text-center">
				<g:if test="${myBets[gameInstance]}">
					${myBets[gameInstance].score1} : ${myBets[gameInstance].score2}
				</g:if>
				<g:else>
					- <sec:ifLoggedIn><g:link controller="bet" action="create" params="['game.id': gameInstance.id]" ><i class="glyphicon glyphicon-pencil"></i></g:link></sec:ifLoggedIn>
				</g:else>
			</div>
		</div>
  	</g:each>
	
	
</div>

</div>
	
</section>

</body>

</html>
