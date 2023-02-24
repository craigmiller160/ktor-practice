package io.craigmiller160.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.Application
import javax.sql.DataSource
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.koin

fun Application.createDatabaseDependencies() {
  val config =
      HikariConfig().apply {
        jdbcUrl = environment.config.property("postgres.jdbcUrl").getString()
        username = environment.config.property("postgres.username").getString()
        password = environment.config.property("postgres.password").getString()
      }

  koin {
    val module = module { single<DataSource> { HikariDataSource(config) } }
    modules(module)
  }
}

fun Application.configureDatabase() {
  createDatabaseDependencies()
  val datasource by inject<DataSource>()
  Database.connect(datasource)

  Flyway.configure().dataSource(datasource).load().migrate()
}
