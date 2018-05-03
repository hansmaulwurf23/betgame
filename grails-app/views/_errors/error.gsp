<html>
	<head>
		<title>Fuck.</title>
		<meta name="layout" content="main" />
		<g:set var="layout_nomainmenu"		value="${true}" scope="request"/>
		<g:set var="layout_nosecondarymenu"	value="${true}" scope="request"/>
	</head>

<body>
	<content tag="header">
		<!-- Empty Header -->
	</content>
	
  	<section id="Error" class="">
		<div class="big-message">
			<div style="background-color: rgba(255,255,255,0.5); border-radius: 5px; padding:10px;">
				<div class="container">
				<h1>
					Fuck.
				</h1>
				<p>
					${exception?.message}
				</p>
				<p>
					Am besten sehr oft und sehr schnell die Seite neu laden, falls es sich um ein nicht deterministisches Problem handelt.
				</p>
				
				<div class="actions">
					<a href="${createLink(uri: '/')}" class="btn btn-large btn-primary">
						<i class="glyphicon glyphicon-chevron-left icon-white"></i>
						<g:message code="error.button.backToHome"/>
					</a>
				</div>
			</div>
			</div>
		</div>
	</section>
  
  
  </body>
</html>