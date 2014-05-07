/// see
// http://flnkr.com/2011/06/using-sqlite-with-grails/

dataSource {
	pooled = true
	dbCreate = ""
	url = "jdbc:sqlite:./data/data.db"
	logSql = false
	dialect = "dialect.SQLiteDialect"
	driverClassName = "org.sqlite.JDBC"
	readOnly = "true"
	grails.dbconsole.enabled = true
	properties {
		// see tomcat.apache.org/tomcat-7.0-doc/jdbc-pool.html#Configuring_JDBC_interceptors
		connectionProperties = "date_precision=seconds;date_string_format=yyyy-MM-dd HH:mm:ss"
	}
}

hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}

// environment specific settings
environments {
	development { }
	test { }
	production { }
}
