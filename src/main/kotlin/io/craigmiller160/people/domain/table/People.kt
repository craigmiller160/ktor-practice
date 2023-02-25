package io.craigmiller160.people.domain.table

import org.jetbrains.exposed.dao.id.UUIDTable

object People : UUIDTable("people") {
  val name = varchar("name", length = 255)
  val age = integer("age")
}
