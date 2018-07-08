<%@ page import="de.betgame.Game" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="phat" />
	<title>Scores</title>
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
	
</head>

<body>

<g:render template="statsmenu"></g:render>

<section id="list-ranking" class="first">

	<div class="table-responsive">
    <table class="table table-sm">
        <thead class="thead-dark">
            <tr>
                <th>Name</th>
                <g:each in="${result}" status="i" var="r">
                    <g:set var="game" value="${de.betgame.Game.get(r.key)}"/>
                	<th class="text-center" style="height:80px"><g:link controller="game" action="show" id="${r.key}">${i+1}</g:link>
                        <br/>
                    <bg:flag team="${game.team1}" /><br/>
                    <bg:flag team="${game.team2}" />
                    </th>
                </g:each>
            </tr>
        </thead>
        <tbody>
        	<g:each in="${users}" var="u">
                <tr>
                <td class="${u.id == curUser.id ? 'bg-secondary text-white' : ''}" style="min-width:120px;">${u.display} (${userScores[(u.id)] ?: 0})</td>
                <g:each in="${result}" status="i" var="r">
                	<bg:scoreCell bet="${r.value.get(u.id)?.getAt(0)}"></bg:scoreCell>
                </g:each>
            </tr>
            </g:each>
        </tbody>
    </table>
</div>
</section>
<script type="text/javascript">
var $table = $('.table');
var $fixedColumn = $table.clone().insertBefore($table).addClass('fixed-column');

$fixedColumn.find('th:not(:first-child),td:not(:first-child)').remove();

$fixedColumn.find('tr').each(function (i, elem) {
    $(this).height($table.find('tr:eq(' + i + ')').height());
    $(this).css('background-color', '#FFF').css('min-width', '120px');
});
</script>
</body>

</html>
