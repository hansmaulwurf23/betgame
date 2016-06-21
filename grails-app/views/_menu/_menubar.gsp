<div class="">
	<ul class="nav nav-tabs" data-role="listview" data-split-icon="gear" data-filter="true">
	
		<g:each status="i" var="logicalPropertyName" in="${['home', 'game', 'bet', 'team', 'stats']}">
			<li class="controller${params.controller == logicalPropertyName ? " active" : ""}">
				<g:link controller="${logicalPropertyName}" action="index">
					<g:if test="${logicalPropertyName == 'home'}">
						<i class="glyphicon glyphicon-home"></i>
					</g:if>
					<g:else>
						<g:message code="${logicalPropertyName}.label" default="${logicalPropertyName.capitalize()}"/>
					</g:else>
				</g:link>
			</li>
		</g:each>
		
	</ul>
</div>
