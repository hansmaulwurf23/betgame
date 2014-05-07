<%@ page import="de.betgame.sportdb.Games" %>



			<div class="${hasErrors(bean: gamesInstance, field: 'playAt', 'error')} required">
				<label for="playAt" class="control-label"><g:message code="games.playAt.label" default="Play At" /><span class="required-indicator">*</span></label>
				<div>
					<bs:datePicker name="playAt" precision="day"  value="${gamesInstance?.playAt}"  />
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'playAt', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'group', 'error')} ">
				<label for="group" class="control-label"><g:message code="games.group.label" default="Group" /></label>
				<div>
					<g:select class="form-control" id="group" name="group.id" from="${de.betgame.sportdb.Groups.list()}" optionKey="id" value="${gamesInstance?.group?.id}" class="many-to-one" noSelection="['null': '']"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'group', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'ground', 'error')} ">
				<label for="ground" class="control-label"><g:message code="games.ground.label" default="Ground" /></label>
				<div>
					<g:select class="form-control" id="ground" name="ground.id" from="${de.betgame.sportdb.Grounds.list()}" optionKey="id" value="${gamesInstance?.ground?.id}" class="many-to-one" noSelection="['null': '']"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'ground', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'team1', 'error')} required">
				<label for="team1" class="control-label"><g:message code="games.team1.label" default="Team1" /><span class="required-indicator">*</span></label>
				<div>
					<g:select class="form-control" id="team1" name="team1.id" from="${de.betgame.sportdb.Teams.list()}" optionKey="id" required="" value="${gamesInstance?.team1?.id}" class="many-to-one"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'team1', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'team2', 'error')} required">
				<label for="team2" class="control-label"><g:message code="games.team2.label" default="Team2" /><span class="required-indicator">*</span></label>
				<div>
					<g:select class="form-control" id="team2" name="team2.id" from="${de.betgame.sportdb.Teams.list()}" optionKey="id" required="" value="${gamesInstance?.team2?.id}" class="many-to-one"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'team2', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'key', 'error')} ">
				<label for="key" class="control-label"><g:message code="games.key.label" default="Key" /></label>
				<div>
					<g:textArea class="form-control" name="key" cols="40" rows="5" maxlength="2000000000" value="${gamesInstance?.key}"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'key', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'cityId', 'error')} ">
				<label for="cityId" class="control-label"><g:message code="games.cityId.label" default="City Id" /></label>
				<div>
					<g:field class="form-control" name="cityId" type="number" value="${gamesInstance.cityId}"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'cityId', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'score1', 'error')} ">
				<label for="score1" class="control-label"><g:message code="games.score1.label" default="Score1" /></label>
				<div>
					<g:field class="form-control" name="score1" type="number" value="${gamesInstance.score1}"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'score1', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'score2', 'error')} ">
				<label for="score2" class="control-label"><g:message code="games.score2.label" default="Score2" /></label>
				<div>
					<g:field class="form-control" name="score2" type="number" value="${gamesInstance.score2}"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'score2', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'score1et', 'error')} ">
				<label for="score1et" class="control-label"><g:message code="games.score1et.label" default="Score1et" /></label>
				<div>
					<g:field class="form-control" name="score1et" type="number" value="${gamesInstance.score1et}"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'score1et', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'score2et', 'error')} ">
				<label for="score2et" class="control-label"><g:message code="games.score2et.label" default="Score2et" /></label>
				<div>
					<g:field class="form-control" name="score2et" type="number" value="${gamesInstance.score2et}"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'score2et', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'score1p', 'error')} ">
				<label for="score1p" class="control-label"><g:message code="games.score1p.label" default="Score1p" /></label>
				<div>
					<g:field class="form-control" name="score1p" type="number" value="${gamesInstance.score1p}"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'score1p', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'score2p', 'error')} ">
				<label for="score2p" class="control-label"><g:message code="games.score2p.label" default="Score2p" /></label>
				<div>
					<g:field class="form-control" name="score2p" type="number" value="${gamesInstance.score2p}"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'score2p', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'score1i', 'error')} ">
				<label for="score1i" class="control-label"><g:message code="games.score1i.label" default="Score1i" /></label>
				<div>
					<g:field class="form-control" name="score1i" type="number" value="${gamesInstance.score1i}"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'score1i', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'score2i', 'error')} ">
				<label for="score2i" class="control-label"><g:message code="games.score2i.label" default="Score2i" /></label>
				<div>
					<g:field class="form-control" name="score2i" type="number" value="${gamesInstance.score2i}"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'score2i', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'score1ii', 'error')} ">
				<label for="score1ii" class="control-label"><g:message code="games.score1ii.label" default="Score1ii" /></label>
				<div>
					<g:field class="form-control" name="score1ii" type="number" value="${gamesInstance.score1ii}"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'score1ii', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'score2ii', 'error')} ">
				<label for="score2ii" class="control-label"><g:message code="games.score2ii.label" default="Score2ii" /></label>
				<div>
					<g:field class="form-control" name="score2ii" type="number" value="${gamesInstance.score2ii}"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'score2ii', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'nextGameId', 'error')} ">
				<label for="nextGameId" class="control-label"><g:message code="games.nextGameId.label" default="Next Game Id" /></label>
				<div>
					<g:field class="form-control" name="nextGameId" type="number" value="${gamesInstance.nextGameId}"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'nextGameId', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'prevGameId', 'error')} ">
				<label for="prevGameId" class="control-label"><g:message code="games.prevGameId.label" default="Prev Game Id" /></label>
				<div>
					<g:field class="form-control" name="prevGameId" type="number" value="${gamesInstance.prevGameId}"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'prevGameId', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'winner', 'error')} ">
				<label for="winner" class="control-label"><g:message code="games.winner.label" default="Winner" /></label>
				<div>
					<g:field class="form-control" name="winner" type="number" value="${gamesInstance.winner}"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'winner', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'winner90', 'error')} ">
				<label for="winner90" class="control-label"><g:message code="games.winner90.label" default="Winner90" /></label>
				<div>
					<g:field class="form-control" name="winner90" type="number" value="${gamesInstance.winner90}"/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'winner90', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'createdAt', 'error')} required">
				<label for="createdAt" class="control-label"><g:message code="games.createdAt.label" default="Created At" /><span class="required-indicator">*</span></label>
				<div>
					<bs:datePicker name="createdAt" precision="day"  value="${gamesInstance?.createdAt}"  />
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'createdAt', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'updatedAt', 'error')} required">
				<label for="updatedAt" class="control-label"><g:message code="games.updatedAt.label" default="Updated At" /><span class="required-indicator">*</span></label>
				<div>
					<bs:datePicker name="updatedAt" precision="day"  value="${gamesInstance?.updatedAt}"  />
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'updatedAt', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'home', 'error')} required">
				<label for="home" class="control-label"><g:message code="games.home.label" default="Home" /><span class="required-indicator">*</span></label>
				<div>
					<g:field class="form-control" name="home" type="number" value="${gamesInstance.home}" required=""/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'home', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'knockout', 'error')} required">
				<label for="knockout" class="control-label"><g:message code="games.knockout.label" default="Knockout" /><span class="required-indicator">*</span></label>
				<div>
					<g:field class="form-control" name="knockout" type="number" value="${gamesInstance.knockout}" required=""/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'knockout', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'pos', 'error')} required">
				<label for="pos" class="control-label"><g:message code="games.pos.label" default="Pos" /><span class="required-indicator">*</span></label>
				<div>
					<g:field class="form-control" name="pos" type="number" value="${gamesInstance.pos}" required=""/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'pos', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'postponed', 'error')} required">
				<label for="postponed" class="control-label"><g:message code="games.postponed.label" default="Postponed" /><span class="required-indicator">*</span></label>
				<div>
					<g:field class="form-control" name="postponed" type="number" value="${gamesInstance.postponed}" required=""/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'postponed', 'error')}</span>
				</div>
			</div>

			<div class="${hasErrors(bean: gamesInstance, field: 'roundId', 'error')} required">
				<label for="roundId" class="control-label"><g:message code="games.roundId.label" default="Round Id" /><span class="required-indicator">*</span></label>
				<div>
					<g:field class="form-control" name="roundId" type="number" value="${gamesInstance.roundId}" required=""/>
					<span class="help-inline">${hasErrors(bean: gamesInstance, field: 'roundId', 'error')}</span>
				</div>
			</div>

