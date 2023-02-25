package io.craigmiller160.people.domain.table

import io.craigmiller160.people.dto.PersonRequest
import java.util.UUID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.update

object People : UUIDTable("people") {
  val name = varchar("name", length = 255)
  val age = integer("age")
}

fun People.updateWithRequest(id: UUID, request: PersonRequest): Int =
    People.update({ People.id eq id }) {
      it[name] = request.name
      it[age] = request.age
    }
