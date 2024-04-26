package org.example

import junit.framework.Assert.assertEquals
import org.example.Postgres.withCleanDb
import org.example.PostgresDataSourceBuilder.runMigration
import org.junit.Test

class PostgresMigrationTest {
    @Test
    fun `Migration scripts are applied successfully`() {
        withCleanDb {
            val migrations = runMigration()
            assertEquals(1, migrations)
        }
    }
}
