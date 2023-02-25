package io.craigmiller160.people

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
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
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.update

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
    val dbPerson =
        newSuspendedTransaction(Dispatchers.IO) {
          Person.new {
            name = person.name
            age = person.age
          }
        }
    call.response.status(HttpStatusCode.Created)
    call.respondText(dbPerson.id.value.toString())
  }
}

fun Route.updatePerson() {
  put("/people/{id}") {
    val person = call.receive<PersonRequest>()
    val id = UUID.fromString(call.parameters["id"])
    newSuspendedTransaction(Dispatchers.IO) {
      People.update({ People.id.eq(id) }) {
        it[name] = person.name
        it[age] = person.age
      }
    }
    call.response.status(HttpStatusCode.NoContent)
  }
}

fun Route.getPerson() {
  get("/people/{id}") {
    val id = UUID.fromString(call.parameters["id"])
    val dbPerson = newSuspendedTransaction(Dispatchers.IO) { Person.findById(id)!! }
    call.respond(PersonResponse(id = dbPerson.id.value, name = dbPerson.name, age = dbPerson.age))
  }
}

fun Route.deletePerson() {
  delete("/people/{id}") {
    val id = UUID.fromString(call.parameters["id"])
    newSuspendedTransaction(Dispatchers.IO) { People.deleteWhere { People.id.eq(id) } }
    call.response.status(HttpStatusCode.NoContent)
  }
}

fun Route.getAllPeople() {
  get("/people") {
    val list = newSuspendedTransaction(Dispatchers.IO) { Person.all().toList() }
    call.respond(list.map { PersonResponse(id = it.id.value, name = it.name, age = it.age) })
  }
}
