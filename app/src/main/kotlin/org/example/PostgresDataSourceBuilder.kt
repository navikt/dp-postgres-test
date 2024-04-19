package org.example

import com.zaxxer.hikari.HikariDataSource
import java.lang.System.getProperty
import java.lang.System.getenv

// Understands how to create a data source from environment variables
internal object PostgresDataSourceBuilder {
    private const val DB_USERNAME_KEY = "DB_USERNAME"
    private const val DB_PASSWORD_KEY = "DB_PASSWORD"
    private const val DB_URL_KEY = "DB_JDBC_URL"

    private fun getOrThrow(key: String): String = getenv(key) ?: getProperty(key)

    val dataSource by lazy {
        HikariDataSource().apply {
            jdbcUrl = getOrThrow(DB_URL_KEY)
            username = getOrThrow(DB_USERNAME_KEY)
            password = getOrThrow(DB_PASSWORD_KEY)
            maximumPoolSize = 10
            minimumIdle = 1
            idleTimeout = 10001
            connectionTimeout = 1000
            maxLifetime = 30001
            initializationFailTimeout = 5000
        }
    }
}
