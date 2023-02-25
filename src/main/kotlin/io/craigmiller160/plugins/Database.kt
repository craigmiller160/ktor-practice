package io.craigmiller160.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.Application
import javax.sql.DataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.koin.core.context.GlobalContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.koin

fun Application.databaseModule() {
  koin()
  configureDatabase()
}

fun Application.koin() {
  val config =
      HikariConfig().apply {
        jdbcUrl = environment.config.property("postgres.jdbcUrl").getString()
        username = environment.config.property("postgres.username").getString()
        password = environment.config.property("postgres.password").getString()
      }

  val postgresPool = Dispatchers.IO.limitedParallelism(10)

  koin {
    val module = module {
      single<DataSource> { HikariDataSource(config) }
      single<CoroutineDispatcher>(named("postgresPool")) { postgresPool }
    }
    modules(module)
  }
}

fun Application.configureDatabase() {
  val datasource by inject<DataSource>()
  Database.connect(datasource)

  Flyway.configure().dataSource(datasource).load().migrate()
}

suspend fun <T> appTransaction(
    db: Database? = null,
    transactionIsolation: Int? = null,
    statement: suspend Transaction.() -> T
): T {
  val postgresPoolDispatcher = GlobalContext.get().get<CoroutineDispatcher>(named("postgresPool"))
  return newSuspendedTransaction(postgresPoolDispatcher, db, transactionIsolation, statement)
}
