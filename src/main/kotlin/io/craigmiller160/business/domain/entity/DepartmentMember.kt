package io.craigmiller160.business.domain.entity

import io.craigmiller160.business.domain.table.DepartmentMembers
import java.util.UUID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class DepartmentMember(id: EntityID<UUID>) : UUIDEntity(id) {
  companion object : UUIDEntityClass<DepartmentMember>(DepartmentMembers)

  var businessId by DepartmentMembers.businessId
  var employeeId by DepartmentMembers.employeeId
  var departmentId by DepartmentMembers.departmentId
  var isManager by DepartmentMembers.isManager
}
