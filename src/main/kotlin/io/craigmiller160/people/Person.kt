package io.craigmiller160.people

import io.craigmiller160.serializers.UUIDSerializer
import java.util.UUID
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable

@Serializable
data class PersonResponse(
    @Serializable(with = UUIDSerializer::class) val id: UUID,
    val name: String,
    val age: Int
)

@Serializable data class PersonRequest(val name: String, val age: Int)

object People : UUIDTable("people") {
  val name = varchar("name", length = 255)
  val age = integer("age")
}

class Person(id: EntityID<UUID>) : Entity<UUID>(id) {
  companion object : EntityClass<UUID, Person>(People)

  var name by People.name
  var age by People.age
}
