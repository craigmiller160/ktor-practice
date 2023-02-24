package io.craigmiller160.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.Application
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.koin.ktor.ext.inject

fun Application.configureDatabase() {
  val hello by inject<Hello>()
  println("SAY HELLO: ${hello.sayHello()}")
  val config =
      HikariConfig().apply {
        jdbcUrl = environment.config.property("postgres.jdbcUrl").getString()
        username = environment.config.property("postgres.username").getString()
        password = environment.config.property("postgres.password").getString()
      }

  val datasource = HikariDataSource(config)
  Database.connect(datasource)

  Flyway.configure().dataSource(datasource).load().migrate()
}
