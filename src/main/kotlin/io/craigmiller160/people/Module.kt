package io.craigmiller160.people

import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.peopleModule() {
  routing { peopleRoutes() }
}
