<li class="dropdown">
	<a class="dropdown-toggle" data-toggle="collapse" href="#controllerCollapse" role="button" aria-expanded="false" aria-controls="controllerCollapse">
		Browse <b class="caret"></b></a>
	
	<ul class="collapse" id="controllerCollapse">
		<g:each var="logicalPropertyName" in="${['home', 'game', 'bet', 'team']}">
			<li class="controller dropdown">
				<g:link controller="${logicalPropertyName}">
					<g:if test="${logicalPropertyName.contains('home')}"><i class="glyphicon glyphicon-home"></i></g:if>
					<g:if test="${logicalPropertyName.contains('game')}"><i class="glyphicon glyphicon-tower"></i></g:if>
					<g:if test="${logicalPropertyName.contains('team')}"><i class="glyphicon glyphicon-globe"></i></g:if>
					<g:if test="${logicalPropertyName.contains('bet')}"> <i class="glyphicon glyphicon-fire"></i></g:if>
					<g:message code="${logicalPropertyName}.label" />
				</g:link>
			</li>
		</g:each>
	</ul>
</li>
