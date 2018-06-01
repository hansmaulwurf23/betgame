<sec:ifNotLoggedIn>

    <li class="nav-item">
        <g:link class="nav-link" controller="home" action="auth">
            <!-- TODO: integrate Springsource Security etc. and show User's name ... -->
            <i class="glyphicon glyphicon-user"></i>
            <g:message code="security.signin.label"/>
        </g:link>
    </li>

</sec:ifNotLoggedIn>
<sec:ifLoggedIn>
    <li class="nav-item dropdown">
            <a class="dropdown-toggle nav-link"  data-toggle="dropdown" href="#userCollapse" role="button" aria-expanded="false" aria-controls="userCollapse">
                <!-- TODO: Only show menu items based on permissions (e.g., Guest has no account page) -->
                <i class="glyphicon glyphicon-user icon-white"></i>
			<sec:loggedInUserInfo field="username"/>
			<b class="caret"></b>
		</a>
		<div class="dropdown-menu" role="menu" id="userCollapse">
		
			<g:link controller="logout" class="dropdown-item">
				<i class="fas fa-power-off"></i>
				<g:message code="security.signoff.label"/>
			</g:link>
		</div>
    </li>
</sec:ifLoggedIn>

