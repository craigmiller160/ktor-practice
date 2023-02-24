package io.craigmiller160

import io.craigmiller160.plugins.configureRouting
import io.craigmiller160.plugins.configureSecurity
import io.craigmiller160.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.EngineMain
import io.ktor.server.netty.Netty

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
//    configureSecurity()
    configureSerialization()
    configureRouting()
}
