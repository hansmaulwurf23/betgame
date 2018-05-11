<%@ page import="de.betgame.Game" %>
<%@ page import="de.betgame.NameUtil" %>

<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<title><g:message code="games.label" /></title>
</head>

<body>

<div class="btn-toolbar mt-1">
<div class="btn-group" role="group" aria-label="Phasen">
	<g:each in="${phases}" var="p">
		<g:link class="btn btn-secondary ${p == phase ? 'active' : ''}" action="list" params="[phase:p, group: group]">${p}</g:link>
	</g:each>
</div>
</div>

<div class="btn-toolbar my-1">
<div class="btn-group" role="group" aria-label="Gruppen">
	<g:each in="${groups}" var="g">
		<g:link action="$actionName" class="btn btn-secondary ${g == group ? 'active' :''}" params="${[phase:phase, group:g]}">${g}</g:link>
	</g:each>
	<g:link action="$actionName" class="btn btn-secondary ${null == group ? 'active' :''}" params="${[phase:phase, group:null]}">
		<g:message code="all.label" />
	</g:link>
</div>
</div>

<section id="list-game" class="first">

	<table class="table table-striped table-sm mt-2">
		<thead class="thead-dark">
        <tr>
            <th>Datum</th>
			<g:if test="${!group}">
            	<th>G</th>
			</g:if>
            <th class="d-none d-md-table-cell">Austragungsort</th>
            <th colspan="3" class="text-center">Spiel</th>
        </tr>
		</thead>
		<tbody>
		<g:each in="${games}" status="i" var="game">
			<tr class="${game.matchIsFinished ? 'active' : ''}">
				<g:set var="notbet" value="${!(game.id in gameIDsFromBets) }" />
				<td><g:link action="show" id="${game.id}" class="${notbet ? 'text-danger' : ''}">
					<g:formatDate date="${game?.playAt}" formatName="default.gamedate.format" />${notbet ? '!' : ''}
				</g:link></td>
				<g:if test="${!group}">
					<g:set var="curGroup" value="${game?.team1?.groupName == game?.team2?.groupName ? game?.team1?.groupName : null}" />
					<g:if test="${curGroup}">
						<td><g:link action="list" params="[group: curGroup]">${curGroup}</g:link></td>
					</g:if>
					<g:else>
						<td></td>
					</g:else>
				</g:if>
                <td class="d-none d-md-table-cell">${game?.location?.city}</td>
				<td style="text-align: right;"><bg:respTeam team="${game.team1}"/> <bg:flag team="${game.team1}" /></td>
				<td style="text-align: center;">${game.finalScore1}:${game.finalScore2}</td>
				<td><bg:flag team="${game.team2}" /> <bg:respTeam team="${game.team1}"/></td>
			</tr>
		</g:each>
		</tbody>
	</table>
</section>

</body>

</html>
