package io.craigmiller160.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.Application
import javax.sql.DataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.ktor.plugin.koin

fun Application.dependencies() {
  val config =
      HikariConfig().apply {
        jdbcUrl = environment.config.property("postgres.jdbcUrl").getString()
        username = environment.config.property("postgres.username").getString()
        password = environment.config.property("postgres.password").getString()
        maximumPoolSize = environment.config.property("postgres.maxPoolSize").getString().toInt()
      }

  val postgresPool = Dispatchers.IO.limitedParallelism(config.maximumPoolSize)

  koin {
    val module = module {
      single<DataSource> { HikariDataSource(config) }
      single<CoroutineDispatcher>(named("postgresPool")) { postgresPool }
    }
    modules(module)
  }
}
