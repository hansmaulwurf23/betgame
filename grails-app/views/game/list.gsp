<%@ page import="de.betgame.Game" %>
<%@ page import="de.betgame.NameUtil" %>

<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<title><g:message code="games.label" /></title>
</head>

<body>


<div class="btn-group mt-1">
    <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="phasenMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        ${params.phase ?: message(code:'all.label')}
    </a>

    <div class="dropdown-menu" aria-labelledby="phasenMenuLink">
        <g:each in="${phases}" var="p">
            <g:link class="dropdown-item ${p == phase ? 'active' : ''}" action="list" params="[phase:p, group: group]">${p}</g:link>
        </g:each>
        <div class="dropdown-divider"></div>
        <g:link action="$actionName" class="dropdown-item ${null == phase ? 'active' :''}" params="${[phase:null, group:group]}">
            <g:message code="all.label" />
        </g:link>
    </div>
</div>

<div class="btn-group mt-1">
    <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="groupMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        ${params.group ?: message(code:'all.label')}
    </a>

    <div class="dropdown-menu" aria-labelledby="groupMenuLink">
        <g:each in="${groups.findAll { it }}" var="g">
            <g:link action="$actionName" class="dropdown-item ${g == group ? 'active' :''}" params="${[phase:phase, group:g]}">${g}</g:link>
        </g:each>
        <div class="dropdown-divider"></div>
        <g:link action="$actionName" class="dropdown-item ${null == group ? 'active' :''}" params="${[phase:phase, group:null]}">
            <g:message code="all.label" />
        </g:link>
    </div>
</div>

<section id="list-game" class="first">

	<table class="table table-striped bg-white table-sm mt-2">
		<thead class="thead-dark">
        <tr>
            <th>Datum</th>
            <th class="d-none d-md-table-cell">Austragungsort</th>
            <th class="d-none d-lg-table-cell">Stadion</th>
			<g:if test="${!group}">
				<th>G</th>
			</g:if>
			<th>P</th>
			<th class="d-none d-lg-table-cell">Tipp</th>
            <th colspan="3" class="text-center">Spiel</th>
        </tr>
		</thead>
		<tbody>
		<g:each in="${games?.sort { it.playAtUTC }?.reverse()}" status="i" var="game">
			<tr class="${game.matchIsFinished ? 'active' : ''}">
				<g:set var="notbet" value="${!(game.id in gameIDsFromBets) }" />
				<td><g:link action="show" id="${game.id}" class="${notbet ? 'text-danger' : ''}">
					<g:formatDate date="${game?.playAt}" formatName="default.gamelist.format" />${notbet ? '!' : ''}
				</g:link></td>
                <td class="d-none d-md-table-cell">${game?.location?.city}</td>
                <td class="d-none d-lg-table-cell">${game?.location?.stadium}</td>
				<g:if test="${!group}">
					<g:set var="curGroup" value="${game?.team1?.groupName == game?.team2?.groupName ? game?.team1?.groupName : null}" />
					<g:if test="${curGroup}">
						<td><g:link action="list" params="[group: curGroup]">${curGroup}</g:link></td>
					</g:if>
					<g:else>
						<td></td>
					</g:else>
				</g:if>

				<g:set var="betInstance" value="${gameToBetMap[game.id]}"/>
				<td><bg:score bet="${betInstance}" /></td>
				<td class="d-none d-lg-table-cell"><g:if test="${betInstance}">
					${betInstance.score1}:${betInstance.score2}</g:if>
				</td>


				<td style="text-align: right;"><bg:respTeam team="${game.team1}"/> <bg:flag team="${game.team1}" /></td>
				<td style="text-align: center;">${game.finalScore1}:${game.finalScore2}</td>
				<td><bg:flag team="${game.team2}" /> <bg:respTeam team="${game.team2}"/></td>
			</tr>
		</g:each>
		</tbody>
	</table>
</section>

</body>

</html>
