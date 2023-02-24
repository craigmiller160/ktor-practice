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
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

private val people = mutableListOf<OldPerson>(OldPerson("Bob", 30))

fun Routing.peopleRoutes() {
  createPerson()
  updatePerson()
  getPerson()
  deletePerson()
  getAllPeople()
}

fun Route.createPerson() {
  post("/people") {
    val oldPerson = call.receive<OldPerson>()
    people += oldPerson
    call.response.status(HttpStatusCode.Created)
  }
}

fun Route.updatePerson() {
  put("/people/{index}") {
    val oldPerson = call.receive<OldPerson>()
    val index = call.parameters["index"]?.toInt()
    people[index!!] = oldPerson
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
    val list = transaction { People.selectAll().toList() }
    println(list)
    val list2 = transaction { Person.all().toList() }
    println(list2)
    call.respondText("Working")
  }
}
