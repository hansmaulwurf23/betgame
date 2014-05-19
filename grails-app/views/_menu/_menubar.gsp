<div class="">
	<ul class="nav nav-tabs" data-role="listview" data-split-icon="gear" data-filter="true">
	
		<g:set var="ignoreControllers" value="['login', 'logout', 'dbdoc', 'events']" />
	
		<g:each status="i" var="logicalPropertyName" in="${['home', 'games', 'bet', 'teams']}">
			<li class="controller${params.controller == logicalPropertyName ? " active" : ""}">
				<g:link controller="${logicalPropertyName}" action="index">
					<g:message code="${logicalPropertyName}.label" default="${logicalPropertyName.capitalize()}"/>
				</g:link>
			</li>
		</g:each>
		
	</ul>
</div>
