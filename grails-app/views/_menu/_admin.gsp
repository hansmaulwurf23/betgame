<sec:ifAnyGranted roles="ROLE_MAILADMIN, ROLE_IDMADMIN">

<li class="nav-item dropdown">
	<a class="dropdown-toggle nav-link " data-toggle="dropdown" href="#adminCollapse" role="button" aria-expanded="false" aria-controls="adminCollapse">
   		<i class="fas fa-wrench"></i>
		<g:message code="default.admin.label"/><b class="caret"></b>
	</a>
	<div class="dropdown-menu" id="adminCollapse">
		<g:link class="dropdown-item ${params.controller == 'admin' ? " active" : ""}" controller="admin" action="countryNames"><i class="fas fa-globe"></i> CountryNames</g:link>
		<g:link class="dropdown-item ${params.controller == 'quartz' ? " active" : ""}" controller="quartz" action="list"><i class="far fa-clock"></i> Quartz</g:link>
		<g:link class="dropdown-item ${params.controller == 'script' ? " active" : ""}" controller="script" action="editor"><i class="far fa-file-alt"></i> Script</g:link>
	</div>
</li>

</sec:ifAnyGranted>