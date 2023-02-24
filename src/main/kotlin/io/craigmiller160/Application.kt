package io.craigmiller160

import io.craigmiller160.people.peopleRoutes
import io.craigmiller160.plugins.configureDatabase
import io.craigmiller160.plugins.configureKoin
import io.craigmiller160.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import io.ktor.server.routing.routing

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
  configureKoin()
  configureDatabase()
  configureSerialization()
  routing { peopleRoutes() }
}
