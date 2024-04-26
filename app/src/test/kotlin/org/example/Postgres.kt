package org.example

import org.flywaydb.core.internal.configuration.ConfigUtils
import org.testcontainers.containers.PostgreSQLContainer

internal object Postgres {
    private val instance by lazy {
        PostgreSQLContainer<Nothing>("postgres:15.2").apply {
            withReuse(true)
            start()
        }
    }

    fun withMigratedDb(block: () -> Unit) {
        withCleanDb {
            PostgresDataSourceBuilder.runMigration()
            block()
        }
    }

    fun withCleanDb(block: () -> Unit) {
        setup()
        PostgresDataSourceBuilder.clean().run {
            block()
        }.also {
            tearDown()
        }
    }

    private fun setup() {
        System.setProperty(ConfigUtils.CLEAN_DISABLED, "false")
        System.setProperty(
            PostgresDataSourceBuilder.DB_URL_KEY,
            instance.jdbcUrl + "&user=${instance.username}&password=${instance.password}",
        )
    }

    private fun tearDown() {
        System.clearProperty(ConfigUtils.CLEAN_DISABLED)
        System.clearProperty(PostgresDataSourceBuilder.DB_URL_KEY)
    }
}
