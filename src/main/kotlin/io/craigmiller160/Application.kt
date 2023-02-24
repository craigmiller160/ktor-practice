package io.craigmiller160

import io.craigmiller160.plugins.configureKoin
import io.craigmiller160.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.rootModule() {
  configureKoin()
  configureSerialization()
}
