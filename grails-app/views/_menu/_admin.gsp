<li class="dropdown">
	<a class="dropdown-toggle" data-toggle="collapse" href="#adminCollapse" role="button" aria-expanded="false" aria-controls="adminCollapse">
   		<i class="glyphicon glyphicon-wrench"></i>
		<g:message code="default.admin.label"/><b class="caret"></b>
	</a>
	<ul class="collapse" id="adminCollapse">
		<li class="">
			<a href="${createLink(uri: '/systeminfo')}">
				<i class="fas fa-info-circle"></i>
				<g:message code="default.systeminfo.label"/>
			</a>
		</li>
		
		<sec:ifAnyGranted roles="ROLE_MAILADMIN">
			<li class="controller${params.controller == 'quartz' ? " active" : ""}">
				<g:link controller="quartz" action="list"><i class="far fa-clock"></i> Quartz</g:link>
			</li>

			<li class="controller${params.controller == 'script' ? " active" : ""}">
				<g:link controller="script" action="editor"><i class="far fa-file-alt"></i> Script</g:link>
			</li>
		</sec:ifAnyGranted>
	</ul>
</li>
