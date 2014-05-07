
<%@ page import="de.betgame.sportdb.Games" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'games.label', default: 'Games')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-games" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.playAt.label" default="Play At" /></td>
				
				<td valign="top" class="value"><g:formatDate date="${gamesInstance?.playAt}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.group.label" default="Group" /></td>
				
				<td valign="top" class="value"><g:link controller="groups" action="show" id="${gamesInstance?.group?.id}">${gamesInstance?.group?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.ground.label" default="Ground" /></td>
				
				<td valign="top" class="value"><g:link controller="grounds" action="show" id="${gamesInstance?.ground?.id}">${gamesInstance?.ground?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.team1.label" default="Team1" /></td>
				
				<td valign="top" class="value"><g:link controller="teams" action="show" id="${gamesInstance?.team1?.id}">${gamesInstance?.team1?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.team2.label" default="Team2" /></td>
				
				<td valign="top" class="value"><g:link controller="teams" action="show" id="${gamesInstance?.team2?.id}">${gamesInstance?.team2?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.key.label" default="Key" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "key")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.cityId.label" default="City Id" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "cityId")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.score1.label" default="Score1" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "score1")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.score2.label" default="Score2" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "score2")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.score1et.label" default="Score1et" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "score1et")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.score2et.label" default="Score2et" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "score2et")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.score1p.label" default="Score1p" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "score1p")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.score2p.label" default="Score2p" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "score2p")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.score1i.label" default="Score1i" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "score1i")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.score2i.label" default="Score2i" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "score2i")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.score1ii.label" default="Score1ii" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "score1ii")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.score2ii.label" default="Score2ii" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "score2ii")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.nextGameId.label" default="Next Game Id" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "nextGameId")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.prevGameId.label" default="Prev Game Id" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "prevGameId")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.winner.label" default="Winner" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "winner")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.winner90.label" default="Winner90" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "winner90")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.createdAt.label" default="Created At" /></td>
				
				<td valign="top" class="value"><g:formatDate date="${gamesInstance?.createdAt}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.updatedAt.label" default="Updated At" /></td>
				
				<td valign="top" class="value"><g:formatDate date="${gamesInstance?.updatedAt}" /></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.home.label" default="Home" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "home")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.knockout.label" default="Knockout" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "knockout")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.pos.label" default="Pos" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "pos")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.postponed.label" default="Postponed" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "postponed")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="games.roundId.label" default="Round Id" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: gamesInstance, field: "roundId")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
