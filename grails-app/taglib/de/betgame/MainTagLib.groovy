package de.betgame

/**
 * MainTagLib
 * A taglib library provides a set of reusable tags to help rendering the views.
 */
class MainTagLib {
    static defaultEncodeAs = 'raw'
    //static encodeAsForTags = [tagName: 'raw']
	
	static namespace = "bg"
	
	def springSecurityService
	def statsService
	
	def flag = { attrs, body ->
		def imgLink
		def imgBaseDir = '/images/flags/'
		def countryCode = attrs.net		
		
		if (countryCode) {
			imgLink = g.resource(dir:imgBaseDir,file:"${countryCode}.png")
			out << "<img src='${imgLink}' alt='${attrs.name}'/>"
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
	
	def gameBets = { attrs, body ->
		if (attrs.game) {
			def showBets = (attrs.game.playAt < new Date())
			def betInstances = Bet.findAllByGame(attrs.game)
			def stats = [:]
			stats.totalCount = betInstances.size()
			stats.averagePoints = betInstances.sum {statsService.getScore(attrs.game, it)} / betInstances.size()
			betInstances.sort { a,b -> statsService.getScore(attrs.game, b) <=> statsService.getScore(attrs.game, a) ?: a.user.givenname <=> b.user.givenname}
			def nameMap = NameUtil.buildNameMap(betInstances*.user)
			out << render(template:"/game/bets", model:[gameInstance: attrs.game, showBets: showBets, betInstances:betInstances, stats:stats, currentUser:springSecurityService.currentUser, nameMap:nameMap])
		}
	}
	
	def score = { attrs, body ->
		if (attrs.game && attrs.bet) {
			def score = statsService.getScore(attrs.game, attrs.bet)
			out << (score!=null?score:g.message(code:'na´', default:'na'))
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
