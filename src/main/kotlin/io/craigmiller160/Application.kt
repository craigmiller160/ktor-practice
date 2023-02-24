package io.craigmiller160

import io.craigmiller160.people.peopleRoutes
import io.craigmiller160.plugins.configureRouting
import io.craigmiller160.plugins.configureSecurity
import io.craigmiller160.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.EngineMain
import io.ktor.server.netty.Netty
import io.ktor.server.routing.Routing
import io.ktor.server.routing.routing
import org.jetbrains.exposed.sql.Database
import javax.sql.DataSource

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureSerialization()
    routing {
        peopleRoutes()
    }
}