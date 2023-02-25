package io.craigmiller160.people

import io.ktor.server.application.Application
import io.ktor.server.application.log
import io.ktor.server.routing.routing

fun Application.peopleModule() {
  log.info("Hello World")
  routing { peopleRoutes() }
}
