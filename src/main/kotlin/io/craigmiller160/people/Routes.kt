package io.craigmiller160.people

import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get

private val people = mutableListOf<Person>(Person("Bob", 30))

fun Routing.peopleRoutes() {
//    createPerson()
//    updatePerson()
//    getPerson()
//    deletePerson()
    getAllPeople()
}

fun Route.createPerson() {

}

fun Route.updatePerson() {

}

fun Route.getPerson() {

}

fun Route.deletePerson() {

}

fun Route.getAllPeople() {
    get("/people") {
        call.respond(people)
    }
}