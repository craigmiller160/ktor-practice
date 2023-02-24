package io.craigmiller160.plugins

import io.ktor.server.application.*
import io.ktor.server.application.Application
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
  install(Koin) {}
}
