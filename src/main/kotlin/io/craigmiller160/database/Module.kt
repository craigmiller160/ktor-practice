package io.craigmiller160.database

import io.ktor.server.application.Application
import javax.sql.DataSource
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.koin.ktor.ext.get

fun Application.databaseModule() {
  dependencies()
  initializeDatabase()
}

fun Application.initializeDatabase() {
  val datasource = get<DataSource>()
  Database.connect(datasource)

  Flyway.configure().dataSource(datasource).load().migrate()
}
