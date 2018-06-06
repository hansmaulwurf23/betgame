<nav id="Navbar" class="navbar navbar-fixed-top navbar-dark bg-dark navbar-expand-sm" role="navigation">
	<div class="container">

        <a class="navbar-brand" href="${createLink(controller: 'home')}">
            <asset:image class="logo" src="brand_logo.png" alt="Betgame" width="16px" height="16px"/>
            ${meta(name:'info.app.name')}
            <small> ${meta(name:'info.app.version')}</small>
        </a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

		<div class="collapse navbar-collapse" id="navbarCollapse" role="navigation">

			<ul class="navbar-nav mr-auto">

                <li class="nav-item dropdown">
                    <a class="dropdown-toggle nav-link" data-toggle="dropdown" href="#controllerCollapse" role="button" aria-expanded="false" aria-controls="controllerCollapse">
                        <i class="fas fa-caret-square-down"></i>
                        Browse<b class="caret"></b></a>

                    <div class="dropdown-menu" id="controllerCollapse">
                        <g:each var="logicalPropertyName" in="${['home', 'game', 'bet', 'team']}">
                            <g:link controller="${logicalPropertyName}" class="dropdown-item">
                                <g:if test="${logicalPropertyName.contains('home')}"><i class="fas fa-home"></i></g:if>
                                <g:if test="${logicalPropertyName.contains('game')}"><i class="fas fa-futbol"></i></g:if>
                                <g:if test="${logicalPropertyName.contains('team')}"><i class="fas fa-globe""></i></g:if>
                                <g:if test="${logicalPropertyName.contains('bet')}"> <i class="fas fa-fire"></i></g:if>
                                <g:message code="${logicalPropertyName}.label" />
                            </g:link>
                        </g:each>
                    </div>
                </li>
			</ul>

			<ul class="navbar-nav">
				<g:render template="/_menu/admin"/>
				<g:render template="/_menu/user"/><!-- NOTE: the renderDialog for the "Register" modal dialog MUST be placed outside the NavBar (at least for Bootstrap 2.1.1): see bottom of main.gsp -->
				<g:render template="/_menu/language"/>
			</ul>

		</div>
	</div>
</nav>
