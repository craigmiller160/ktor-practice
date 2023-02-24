package io.craigmiller160.people

import java.util.UUID
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable data class OldPerson(val name: String, val age: Int)

data class Person(val id: UUID, val name: String, val age: Int)

data class PersonRequest(val name: String, val age: Int)

object People : Table("people") {
  val id = uuid("id")
  val name = varchar("name", length = 255)
  val age = integer("age")

  override val primaryKey = PrimaryKey(id)
}
