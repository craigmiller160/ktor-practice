package io.craigmiller160.people

import io.craigmiller160.database.appTransaction
import io.craigmiller160.people.domain.entity.Person
import io.craigmiller160.people.domain.entity.newWithRequest
import io.craigmiller160.people.domain.entity.toResponse
import io.craigmiller160.people.domain.table.People
import io.craigmiller160.people.domain.table.updateWithRequest
import io.craigmiller160.people.dto.PersonRequest
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.application
import io.ktor.server.application.call
import io.ktor.server.application.log
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import java.util.UUID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

fun Routing.peopleRoutes() {
  createPerson()
  updatePerson()
  getPerson()
  deletePerson()
  getAllPeople()
}

fun Route.createPerson() {
  post("/people") {
    application.log.info("Create Person")
    val person = call.receive<PersonRequest>()
    val dbPerson = appTransaction { Person.newWithRequest(person) }
    call.response.status(HttpStatusCode.Created)
    call.respondText(dbPerson.id.value.toString())
  }
}

fun Route.updatePerson() {
  put("/people/{id}") {
    application.log.info("Update Person")
    val person = call.receive<PersonRequest>()
    val id = UUID.fromString(call.parameters["id"])
    appTransaction { People.updateWithRequest(id, person) }
    call.response.status(HttpStatusCode.NoContent)
  }
}

fun Route.getPerson() {
  get("/people/{id}") {
    application.log.info("Get Person")
    val id = UUID.fromString(call.parameters["id"])
    appTransaction { Person.findById(id) }?.let { call.respond(it.toResponse()) }
        ?: call.response.status(HttpStatusCode.NoContent)
  }
}

fun Route.deletePerson() {
  delete("/people/{id}") {
    application.log.info("Delete Person")
    val id = UUID.fromString(call.parameters["id"])
    appTransaction { People.deleteWhere { People.id eq id } }
    call.response.status(HttpStatusCode.NoContent)
  }
}

fun Route.getAllPeople() {
  get("/people") { application.peopleService().getAllPeople().let { call.respond(it) } }
}
