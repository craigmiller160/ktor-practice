package io.craigmiller160.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/hello2") {
            call.respond(mapOf("hello" to "world"))
        }
        route("/nested") {
            get("/hello") {
                call.respondText("Nested Hello")
            }
        }
    }
}