<%@ page import="de.betgame.Game" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
	<title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>

<body>

	<section id="edit-game" class="first">

		<g:hasErrors bean="${gameInstance}">
		<div class="alert alert-danger">
			<g:renderErrors bean="${gameInstance}" as="list" />
		</div>
		</g:hasErrors>

		<g:form method="post" class="form-horizontal" role="form" >
			<g:hiddenField name="id" value="${gameInstance?.id}" />
			<g:hiddenField name="version" value="${gameInstance?.version}" />
			<g:hiddenField name="_method" value="PUT" />
			
			<div class="panel panel-default">
	
				<div class="row panel-body">	
					<div class="col-xs-5">
						<label for="score1">${gameInstance.team1.code}</label>
						<g:field class="form-control" name="score1" type="number" value="${gameInstance.score1 ?: 0}" min="0" required=""/>
						<span class="help-inline">${hasErrors(bean: gameInstance, field: 'score1', 'error')}</span>
					</div>
					<div class="col-xs-2 text-center"> : </div>
					<div class="col-xs-5">
						<label for="score2">${gameInstance.team2.code}</label>	
						<g:field class="form-control" name="score2" type="number" value="${gameInstance.score2 ?: 0}" min="0" required=""/>
						<span class="help-inline">${hasErrors(bean: gameInstance, field: 'score2', 'error')}</span>
					</div>
				</div>
				
				<div class="row panel-body">	
					<div class="col-xs-12">
						<label for="score1">Finished?</label>
						<g:checkBox name="matchIsFinished" value="${gameInstance.matchIsFinished}"/>
					</div>
				</div>
				
			</div>
			
			<div class="form-actions margin-top-medium">
				<g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
	            <button class="btn" type="reset"><g:message code="default.button.reset.label" default="Reset" /></button>
			</div>
		</g:form>

	</section>

</body>

</html>
