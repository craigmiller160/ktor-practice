package io.craigmiller160.business.domain.entity

import io.craigmiller160.business.domain.table.ExecutiveOffices
import java.util.UUID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ExecutiveOffice(id: EntityID<UUID>) : UUIDEntity(id) {
  companion object : UUIDEntityClass<ExecutiveOffice>(ExecutiveOffices)

  var businessId by ExecutiveOffices.businessId
  var shortTitle by ExecutiveOffices.shortTitle
  var longTitle by ExecutiveOffices.longTitle
}
