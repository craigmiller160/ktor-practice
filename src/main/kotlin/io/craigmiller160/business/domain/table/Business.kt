package io.craigmiller160.business.domain.table

import org.jetbrains.exposed.dao.id.UUIDTable

object Businesses : UUIDTable("businesses") {
  val ein = char("ein", 9)
  val name = varchar("name", 255)
}
