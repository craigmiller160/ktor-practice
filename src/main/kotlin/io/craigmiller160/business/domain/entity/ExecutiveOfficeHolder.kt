package io.craigmiller160.business.domain.entity

import io.craigmiller160.business.domain.table.ExecutiveOfficeHolders
import java.util.UUID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ExecutiveOfficeHolder(id: EntityID<UUID>) : UUIDEntity(id) {
  companion object : UUIDEntityClass<ExecutiveOfficeHolder>(ExecutiveOfficeHolders)

  var businessId by ExecutiveOfficeHolders.businessId
  var employeeId by ExecutiveOfficeHolders.employeeId
  var executiveOfficeId by ExecutiveOfficeHolders.executiveOfficeId
}
