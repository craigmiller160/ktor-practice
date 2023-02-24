package io.craigmiller160.people

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put

private val people = mutableListOf<Person>(Person("Bob", 30))

fun Routing.peopleRoutes() {
    createPerson()
    updatePerson()
    getPerson()
    deletePerson()
    getAllPeople()
}

fun Route.createPerson() {
    post("/people") {
        val person = call.receive<Person>()
        people += person
        call.response.status(HttpStatusCode.Created)
    }
}

fun Route.updatePerson() {
    put("/people/{index}") {
        val person = call.receive<Person>()
        val index = call.parameters["index"]?.toInt()
        people[index!!] = person
        call.response.status(HttpStatusCode.NoContent)
    }
}

fun Route.getPerson() {
    get("/people/{index}") {
        val index = call.parameters["index"]?.toInt()
        call.respond(people[index!!])
    }
}

fun Route.deletePerson() {
    delete("/people/{index}") {
        val index = call.parameters["index"]?.toInt()
        people.removeAt(index!!)
        call.response.status(HttpStatusCode.NoContent)
    }
}

fun Route.getAllPeople() {
    get("/people") {
        call.respond(people)
    }
}