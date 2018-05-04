<li class="dropdown">
	<a class="dropdown-toggle" data-toggle="collapse" href="#adminCollapse" role="button" aria-expanded="false" aria-controls="adminCollapse">
   		<i class="glyphicon glyphicon-wrench"></i>
		<g:message code="default.admin.label"/><b class="caret"></b>
	</a>
	<ul class="collapse" id="adminCollapse">
		<li class="">
			<a href="${createLink(uri: '/systeminfo')}">
				<i class="glyphicon glyphicon-info-sign"></i>
				<g:message code="default.systeminfo.label"/>
			</a>
		</li>
		
		<sec:ifAnyGranted roles="ROLE_MAILADMIN">
			<li class="controller${params.controller == 'quartz' ? " active" : ""}">
				<g:link controller="quartz" action="list"><i class="glyphicon glyphicon-time"></i> Quartz</g:link>
			</li>
		</sec:ifAnyGranted>
	</ul>
</li>
