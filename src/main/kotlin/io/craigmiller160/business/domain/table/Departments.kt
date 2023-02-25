package io.craigmiller160.business.domain.table

import io.craigmiller160.business.domain.table.ExecutiveOffices.references
import org.jetbrains.exposed.dao.id.UUIDTable

object Departments : UUIDTable("departments") {
  val businessId = uuid("business_id").references(Businesses.id)
  val name = varchar("name", 255)
}
