package io.craigmiller160.plugins

import io.ktor.server.application.*
import io.ktor.server.application.Application
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

class Hello {
  fun sayHello(): String = "Hello World"
}

fun Application.configureKoin() {
  install(Koin) {
    val module = module { single { Hello() } }
    modules(module)
  }
}
