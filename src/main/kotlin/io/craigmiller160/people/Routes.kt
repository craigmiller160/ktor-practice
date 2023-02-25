package io.craigmiller160.people

import io.craigmiller160.people.dto.PersonRequest
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import java.util.UUID

fun Routing.peopleRoutes() {
  createPerson()
  updatePerson()
  getPerson()
  deletePerson()
  getAllPeople()
}

fun Route.createPerson() {
  post("/people") {
    val person = call.receive<PersonRequest>()
    val response = application.peopleService().createPerson(person)
    call.response.status(HttpStatusCode.Created)
    call.respond(response)
  }
}

fun Route.updatePerson() {
  put("/people/{id}") {
    val person = call.receive<PersonRequest>()
    val id = UUID.fromString(call.parameters["id"])
    if (application.peopleService().updatePerson(id, person)) {
      call.response.status(HttpStatusCode.NoContent)
    } else {
      call.response.status(HttpStatusCode.NotFound)
    }
  }
}

fun Route.getPerson() {
  get("/people/{id}") {
    val id = UUID.fromString(call.parameters["id"])
    application.peopleService().getPerson(id)?.let { call.respond(it) }
        ?: call.response.status(HttpStatusCode.NotFound)
  }
}

fun Route.deletePerson() {
  delete("/people/{id}") {
    val id = UUID.fromString(call.parameters["id"])
    application.peopleService().deletePerson(id)
    call.response.status(HttpStatusCode.NoContent)
  }
}

fun Route.getAllPeople() {
  get("/people") { application.peopleService().getAllPeople().let { call.respond(it) } }
}
