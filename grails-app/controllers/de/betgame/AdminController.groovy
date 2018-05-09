package de.betgame

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.sql.Sql

@Secured(['ROLE_MAILADMIN'])
@Transactional
class AdminController {

    def dataSource
    
    def countryNames() {
        Sql sql = new Sql(dataSource)
        def countryNames = sql.rows("select * from countrynames")
        [countryNames:countryNames]
    }
    
    def updateCountryName(String code, String locale, String name) {
        Sql sql = new Sql(dataSource)
        def affected = sql.rows("select count(*) as num from countrynames where code = $code and locale = $locale")[0].num
        if (affected > 0) {
            affected = sql.executeUpdate("update countrynames set name = $name where code = $code and locale = $locale")
            flash.message = "$affected rows updated!"
        } else {
            sql.executeInsert("insert into countrynames (code, locale, name, fullname) values ($code, $locale, $name, $name)")
            flash.message = "$code $locale $name inserted!"
        }
        
        redirect(action:'countryNames')
    }
    
    def deleteCountyName(String code, String locale) {
        Sql sql = new Sql(dataSource)
        sql.execute("delete from countryCode where code = $code and locale = $locale")
        
        redirect(action:'countryNames')
    }
}
