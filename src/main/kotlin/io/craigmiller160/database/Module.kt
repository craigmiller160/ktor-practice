package io.craigmiller160.database

import io.craigmiller160.plugins.initializeDatabase
import io.ktor.server.application.Application

fun Application.databaseModule() {
  dependencies()
  initializeDatabase()
}
