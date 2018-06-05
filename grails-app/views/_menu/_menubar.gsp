<ul class="nav nav-tabs">

	<g:each status="i" var="logicalPropertyName" in="${['home', 'game', 'bet', 'team', 'stats']}">
		<li class="nav-item controller">
			<g:link controller="${logicalPropertyName}" action="index"
					class="nav-link ${params.controller == logicalPropertyName ? "active" : ""}">
				<g:if test="${logicalPropertyName == 'home'}">
					<i class="fas fa-home"></i>
				</g:if>
				<g:else>
					<g:message code="${logicalPropertyName}.label" default="${logicalPropertyName.capitalize()}"/>
				</g:else>
			</g:link>
		</li>
	</g:each>

</ul>

