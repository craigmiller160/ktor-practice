package io.craigmiller160.people

import io.craigmiller160.database.appTransaction
import io.craigmiller160.people.domain.entity.Person
import io.craigmiller160.people.domain.entity.toResponse
import io.craigmiller160.people.dto.PersonResponse
import io.ktor.server.application.Application
import io.ktor.server.application.application
import io.ktor.server.application.log

class PeopleService(val application: Application)

fun Application.peopleService(): PeopleService = PeopleService()

suspend fun PeopleService.getAllPeople(): List<PersonResponse> {
  application.log.info("Get All People")
  val list = appTransaction { Person.all().toList() }
  return list.map { it.toResponse() }
}
