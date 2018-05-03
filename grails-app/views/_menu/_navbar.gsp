<nav id="Navbar" class="navbar navbar-fixed-top navbar-dark bg-dark" role="navigation">
	<div class="container">
	
	    <div class="navbar-header">
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".navbar-ex1-collapse" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<a class="navbar-brand" href="${createLink(uri: '/')}">
				<asset:image class="logo" src="brand_logo.png" alt="Betgame" width="16px" height="16px"/>
				${meta(name:'app.name')}
				<small> v${meta(name:'app.version')}</small>
			</a>
		</div>

		<div class="collapse navbar-collapse navbar-ex1-collapse" role="navigation">

		<ul class="nav navbar-nav">
			<g:render template="/_menu/controller"/>
		</ul>

    	<ul class="nav navbar-nav navbar-right">
 			<g:render template="/_menu/search"/> 
			<g:render template="/_menu/admin"/>														
			<g:render template="/_menu/info"/>														
			<g:render template="/_menu/user"/><!-- NOTE: the renderDialog for the "Register" modal dialog MUST be placed outside the NavBar (at least for Bootstrap 2.1.1): see bottom of main.gsp -->
			<g:render template="/_menu/language"/>														
	    </ul>			

		</div>
	</div>
</nav>
