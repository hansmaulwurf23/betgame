<li class="dropdown">
	<a class="dropdown-toggle" data-toggle="dropdown" href="#">Browse <b class="caret"></b></a>
	
	<ul class="dropdown-menu">
		<g:each var="logicalPropertyName" in="${['home', 'game', 'bet', 'team']}">
			<li class="controller">
				<g:link controller="${logicalPropertyName}">
					<g:if test="${logicalPropertyName.contains('home')}"><i class="glyphicon glyphicon-home"></i></g:if>
					<g:if test="${logicalPropertyName.contains('game')}"><i class="glyphicon glyphicon-tower"></i></g:if>
					<g:if test="${logicalPropertyName.contains('team')}"><i class="glyphicon glyphicon-globe"></i></g:if>
					<g:if test="${logicalPropertyName.contains('bet')}"> <i class="glyphicon glyphicon-fire"></i></g:if>
					${logicalPropertyName.capitalize()}
				</g:link>
			</li>
		</g:each>
	</ul>
</li>
