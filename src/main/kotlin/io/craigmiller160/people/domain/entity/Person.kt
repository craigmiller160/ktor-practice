package io.craigmiller160.people.domain.entity

import io.craigmiller160.people.People
import java.util.UUID
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Person(id: EntityID<UUID>) : Entity<UUID>(id) {
  companion object : EntityClass<UUID, Person>(People)

  var name by People.name
  var age by People.age
}
