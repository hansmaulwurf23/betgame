<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<title>Scores</title>
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
	
	<style type="text/css">
		table tr th:first-child, table tr td:first-child {
			background-color: #FFF;
		}
	</style>
</head>

<body>

<g:render template="statsmenu"></g:render>

<section id="list-ranking" class="first">

	<div class="table-responsive">
    <table class="table table-hover table-condensed">
        <thead>
            <tr>
                <th>Name</th>
                <g:each in="${result}" status="i" var="r">
                	<th>${i+1}</th>
                </g:each>
            </tr>
        </thead>
        <tbody>
        	<g:each in="${nameMap}" var="u">
            <tr>
                <td>${u.value}</td>
                <g:each in="${result}" status="i" var="r">
                <bg:scoreCell bet="${r.value.get(u.key.id)?.getAt(0)}"></bg:scoreCell>
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
});
</script>
</body>

</html>
