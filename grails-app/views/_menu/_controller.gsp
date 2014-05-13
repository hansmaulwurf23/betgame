<li class="dropdown">
	<a class="dropdown-toggle" data-toggle="dropdown" href="#">Browse <b class="caret"></b></a>
	
	<g:set var="ignoreControllers" value="['login', 'logout', 'dbdoc']" />
	
	<ul class="dropdown-menu">
		<g:each var="logicalPropertyName" in="${['home', 'games', 'grounds', 'teams']}">
			<li class="controller">
				<g:link controller="${logicalPropertyName}">
					<g:if test="${logicalPropertyName.contains('home')}"><i class="glyphicon glyphicon-home"></i></g:if>
					<g:if test="${logicalPropertyName.contains('games')}"><i class="glyphicon glyphicon-tower"></i></g:if>
					<g:if test="${logicalPropertyName.contains('grounds')}"><i class="glyphicon glyphicon-list-alt"></i></g:if>
					<g:if test="${logicalPropertyName.contains('teams')}"><i class="glyphicon glyphicon-globe"></i></g:if>
					${logicalPropertyName.capitalize()}
				</g:link>
			</li>
		</g:each>
	</ul>
</li>
