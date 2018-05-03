<html>
<head>
	<title><g:message code="springSecurity.login.title"/></title>
	<meta name="layout" content="main" />

	<g:set var="layout_nomainmenu"		value="${true}" scope="request"/>
	<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
</head>

<body>

<section id="login" class="first">

<div class="row">
	<div class="col-md-3"></div>
	<div class="col-md-6">
		<form role="form" id='loginForm' class='form-horizontal' action='${postUrl}' method='POST' autocomplete='off'>
		
			<div class="panel panel-default">
	
		
				<div class="panel-heading ">
					<h3> <g:message code="springSecurity.login.header"/> </h3>
				</div>
				
				<div class="panel-body">
				
				<div class="${hasErrors(bean: _DemoPageInstance, field: 'name', 'error')} ">
					<label for='username' class="control-label"><g:message code="springSecurity.login.username.label"/>:</label>
					<div class="controls">
						<input type='text' class='form-control col-md-4' name='j_username' id='username'/>
					</div>
				</div>
	
				<div class="${hasErrors(bean: _DemoPageInstance, field: 'name', 'error')} ">
					<label for='password' class="control-label"><g:message code="springSecurity.login.password.label"/>:</label>
					<div class="controls">
						<input type='password' class='form-control col-md-4' name='j_password' id='password'/>
					</div>
				</div>
				
				</div>
				
				<div class="panel-footer">
					<input type='submit' id="submit" class="btn btn-success" value='${message(code: "springSecurity.login.button")}'/>
				</div>
		</form>
	</div>
	<div class="col-md-3"></div>
</div>
</section>

<script type='text/javascript'>
	<!--
	(function() {
		document.forms['loginForm'].elements['j_username'].focus();
	})();
	// -->
</script>

</body>
</html>
