import de.betgame.*
import de.betgame.sec.User

import groovy.sql.Sql

process {

    def mailService = grailsApplication.mainContext.getBean('mailService')
    def dataSource = grailsApplication.mainContext.getBean('dataSource')
    Sql sql = new Sql(dataSource)

    def tomorrow = (new java.sql.Date(new Date().time).clearTime()) + 1

    def bettingUserIDs = sql.rows("select distinct user_id from bet")*.user_id
    def tomorrowsGameIDs = sql.rows("select game_id from game where play_at >= :d and play_at < :n", [d:tomorrow, n: tomorrow + 1])*.game_id

    log.info "Found ${bettingUserIDs.size()} betting users"
    log.info "Found ${tomorrowsGameIDs.size()} games taking place tomorrow: ${tomorrowsGameIDs}"

    if (!tomorrowsGameIDs) {
        log.warn "No games tomorrow. skipping"
        return
    }

    def q = """
select u.id as user_id
  from sec.users u
  where u.id not in (
    select user_id from (
      select
        user_id,
        count(*)
      from bet b
      where b.game_id in (${tomorrowsGameIDs.join(',')})
      group by user_id
      having count(*) = ${tomorrowsGameIDs.size()}
    ) as allbetters
  ) 
  and u.id in (${bettingUserIDs.join(',')})
    """.toString()

    log.info "\n$q"
    def noobs = sql.rows(q)*.user_id

    if (noobs) {
        log.info "Found noobs: ${noobs.size()}"
        noobs.each { noob ->
            User u = User.get(noob)

            if (u && u.email && !u.nomail) {
                log.warn "${u.email}"
                mailService.sendMail {
                    to u.email
                    from "noreply@localhost"
                    subject "Betgame!"
                    text """Ahoihoi Haubentaucher,

da fehlen wohl noch ein paar Tipps fuer den morgigen Spieltag, wa?

Dein Betgame.
"""
                }
            } else {
                log.warn "Could not mail user $noob since user not found or no mail set or nomail set!"
            }
        }
    } else {
        log.debug "No noobs. (incredibly!)"
    }
}