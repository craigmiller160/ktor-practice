package io.craigmiller160.people

import io.craigmiller160.database.appTransaction
import io.craigmiller160.people.domain.entity.Person
import io.craigmiller160.people.domain.entity.newWithRequest
import io.craigmiller160.people.domain.entity.toResponse
import io.craigmiller160.people.domain.table.People
import io.craigmiller160.people.domain.table.updateWithRequest
import io.craigmiller160.people.dto.PersonRequest
import io.craigmiller160.people.dto.PersonResponse
import io.ktor.server.application.Application
import io.ktor.server.application.application
import io.ktor.server.application.log
import java.util.UUID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class PeopleService(val application: Application)

fun Application.peopleService(): PeopleService = PeopleService(this)

suspend fun PeopleService.getAllPeople(): List<PersonResponse> {
  application.log.info("Get All People")
  val list = appTransaction { Person.all().toList() }
  return list.map { it.toResponse() }
}

suspend fun PeopleService.deletePerson(id: UUID) {
  application.log.info("Delete Person")
  appTransaction { People.deleteWhere { People.id eq id } }
}

suspend fun PeopleService.getPerson(id: UUID): PersonResponse? {
  application.log.info("Get Person")
  return appTransaction { Person.findById(id) }?.toResponse()
}

suspend fun PeopleService.updatePerson(id: UUID, request: PersonRequest): Boolean {
  application.log.info("Update Person")
  return appTransaction { People.updateWithRequest(id, request) } > 0
}

suspend fun PeopleService.createPerson(request: PersonRequest): PersonResponse {
  application.log.info("Create Person")
  return appTransaction { Person.newWithRequest(request) }.toResponse()
}
