dataSource {
	pooled = true
	jmxExport = true
	driverClassName = "org.h2.Driver"
	username = "sa"
	password = ""
}
hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = false
//    cache.region.factory_class = 'org.hibernate.cache.SingletonEhCacheRegionFactory' // Hibernate 3
	cache.region.factory_class = 'org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory' // Hibernate 4
	singleSession = true // configure OSIV singleSession mode
	flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
	TimeZone.setDefault(TimeZone.getTimeZone("UTC"))

	development {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
			url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			pooled = true
			driverClassName = "com.mysql.jdbc.Driver"
			dialect = org.hibernate.dialect.MySQL5InnoDBDialect
			//dialect = org.hibernate.dialect.MySQLMyISAMDialect
			// logSql = true
			username = "foodpark"
			password = "foodpark"
			url = "jdbc:mysql://localhost/foodpark?useUnicode=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull"
			properties {
				minEvictableIdleTimeMillis=1800000
				timeBetweenEvictionRunsMillis=1800000
				numTestsPerEvictionRun=3
				testOnBorrow=true
				testWhileIdle=true
				testOnReturn=true
				validationQuery="SELECT 1"
			}
		}
	}
}
