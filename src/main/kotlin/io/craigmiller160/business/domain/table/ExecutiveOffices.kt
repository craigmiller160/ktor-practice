package io.craigmiller160.business.domain.table

import org.jetbrains.exposed.dao.id.UUIDTable

object ExecutiveOffices : UUIDTable("executive_offices") {
  val businessId = uuid("business_id").references(Businesses.id)
  val shortTitle = char("short_title", 3)
  val longTitle = char("long_title", 255)
}
