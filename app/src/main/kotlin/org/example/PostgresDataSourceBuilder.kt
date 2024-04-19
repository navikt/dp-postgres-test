package org.example

import com.zaxxer.hikari.HikariDataSource
import java.lang.System.getProperty
import java.lang.System.getenv

internal object PostgresDataSourceBuilder {
    private const val DB_URL_KEY = "DB_JDBC_URL"

    private fun getOrThrow(key: String): String = getenv(key) ?: getProperty(key)

    val dataSource by lazy {
        HikariDataSource().apply {
            jdbcUrl = getOrThrow(DB_URL_KEY)
            maximumPoolSize = 10
            minimumIdle = 1
            idleTimeout = 10001
            connectionTimeout = 1000
            maxLifetime = 30001
            initializationFailTimeout = 5000
        }
    }
}
