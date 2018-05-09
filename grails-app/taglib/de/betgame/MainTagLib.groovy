package de.betgame

/**
 * MainTagLib
 * A taglib library provides a set of reusable tags to help rendering the views.
 */
class MainTagLib {
    static defaultEncodeAs = 'raw'
    //static encodeAsForTags = [tagName: 'raw']
	
	static namespace = "bg"
	
	static scoreColors = ['', 'success', 'warning', 'danger']
	static posChangeIconClasses = ['glyphicon-circle-arrow-up text-success', 'glyphicon-circle-arrow-left', 'glyphicon-circle-arrow-down text-danger']
	
	def springSecurityService
	def statsService
	
	def flag = { attrs, body ->
		def imgLink
		def team = attrs.team		
		
		if (team && team.code) {
			out << "<img src='${assetPath(src: 'flags/'+team.code?.toLowerCase()+'.png')}' alt='${attrs.name}'/>"
		}
	}
	
	def localizedGameDate = { attrs, body ->
		if (attrs.game) {
			def locDate = attrs.game.playAt
			if (locDate) {
				out << g.formatDate(date: locDate, type: attrs.type, timeStyle: attrs.timeStyle, dateStyle: attrs.dateStyle)
			} else if (!locDate && !attrs.forceLocalTimeZone) {
				out << g.formatDate(date: attrs.game.playAtUTC, type: attrs.type, timeStyle: attrs.timeStyle, dateStyle: attrs.dateStyle)
				out << "(!)"
			}
		}
	}
	
	def goalInfos = { attrs, body ->
		if (attrs.goal) {
			def goal = attrs.goal
			if (goal.ownGoal)
				out << '<span class="label label-warning">ET</span>'
			if (goal.penalty)
				out << '<span class="label label-danger">EM</span>'
			if (goal.overtime)
				out << '<span class="label label-primary">NZ</span>'
		}
	}
	
	def gameBets = { attrs, body ->
		if (attrs.game) {
			def showBets = (attrs.game.playAt < new Date())
			def betInstances = Bet.findAllByGame(attrs.game)
			def stats = [:]
			stats.totalCount = betInstances.size()
			stats.averagePoints = betInstances.sum { it.getScore() }?.div(betInstances.size())
			betInstances.sort { a,b -> b.getScore() <=> a.getScore() ?: a.goalDiff <=> b.goalDiff ?: a.user.givenname <=> b.user.givenname}
			def nameMap = NameUtil.buildNameMap(betInstances*.user)
			out << render(template:"/game/bets", model:[gameInstance: attrs.game, showBets: showBets, betInstances:betInstances, stats:stats, currentUser:springSecurityService.currentUser, nameMap:nameMap])

			if (attrs.game.matchStarted) {
				def betDistr = betInstances.countBy { "${it.score1}:${it.score2}" }.sort { -it.value }
				out << render(template:"/game/betDistr", model:[betDistr: betDistr, total: stats.totalCount])
			
				def quote = betInstances.countBy { it.score1 <=> it.score2 }.sort { it.key }
				def wiloQuote = [:]
				wiloQuote[(attrs.game.team2.code)] = quote[(-1)]
				wiloQuote['Remis'] = quote[(0)]
				wiloQuote[(attrs.game.team1.code)] = quote[(1)]
				println wiloQuote
				out << render(template:"/game/wiloQuote", model:[distrData: wiloQuote, total: stats.totalCount, titel:'Verteilung'])
			}
		}
	}
	
	def respTeam = { attrs, body ->
		Team t = attrs.team
		if (t) {
			out << "<span class='d-md-none'>${t.code}</span>"
			out << "<span class='d-none d-md-inline-block'>${t.name}</span>"
		}
	}
	
	// FIXME useless tag
	def score = { attrs, body ->
		if (attrs.bet) {
			def score = attrs.bet.getScore()
			if (score != null) {
				out << "<span class='score score_${score}'>${score}</span>"
			} else {
				out << "<span class='score'>${g.message(code:'na´', default:'na')}</span>"
			}
		}
	}
	
	def scoreCell = { attrs, body ->
		if (attrs.bet) {
			def score = attrs.bet.getScore()
			def cellClass = scoreColors[score]
			out << "<td class='text-center $cellClass'>$score</td>"
		} else {
			out << "<td class='text-center active'>-</td>"
		}
	}
	
	def posChangeIcon = { attrs, body ->
		if (attrs.posChange != null) {
			def posChange = Integer.signum(attrs.posChange)
			if (posChange != 0) {
				out << "<i class='glyphicon ${posChangeIconClasses.get(posChange+1)}'></i>"
			}
		} else {
			out << '<i class="glyphicon glyphicon-plus-sign text-muted"></i>'
		}
	}
	
	def gameStatus = { attrs, body ->
		def status = g.message(code:'na', default:'na')
		if (attrs.game) {
			def now = new Date()
			if (attrs.game.playAt > now) {
				status = g.message(code:'upcoming', default:'anstehend')
			} else if (attrs.game.matchIsFinished) {
				status = g.message(code:'finished', default:'beendet')
			} else {
				status = g.message(code:'running', default:'läuft')
			}
		}
		out << status
	}
}
