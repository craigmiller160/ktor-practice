package io.craigmiller160.people.domain.entity

import io.craigmiller160.people.domain.table.People
import io.craigmiller160.people.dto.PersonRequest
import io.craigmiller160.people.dto.PersonResponse
import java.util.UUID
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Person(id: EntityID<UUID>) : Entity<UUID>(id) {
  companion object : EntityClass<UUID, Person>(People)

  var name by People.name
  var age by People.age
}

fun Person.toResponse(): PersonResponse =
    PersonResponse(id = this.id.value, name = this.name, age = this.age)

fun Person.Companion.newWithRequest(request: PersonRequest): Person =
    Person.new {
      name = request.name
      age = request.age
    }
