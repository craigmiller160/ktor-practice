package io.craigmiller160.business.domain.entity

import io.craigmiller160.business.domain.table.Departments
import java.util.UUID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Department(id: EntityID<UUID>) : UUIDEntity(id) {
  companion object : UUIDEntityClass<Department>(Departments)

  var businessId by Departments.businessId
  var name by Departments.name
}
