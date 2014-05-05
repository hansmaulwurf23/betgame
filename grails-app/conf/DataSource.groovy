/// see
// http://flnkr.com/2011/06/using-sqlite-with-grails/

dataSource {
   dbCreate = ""
   url = "jdbc:sqlite:/home/martin/workspace/betgame/data/data.db"
   logSql = false
   dialect = "dialect.SQLiteDialect"
   driverClassName = "org.sqlite.JDBC"
   readOnly = "true"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
    }
    test {
    }
    production {
    }
}
