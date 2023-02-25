package io.craigmiller160.business.domain.entity

import io.craigmiller160.business.domain.table.Employees
import java.util.UUID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Employee(id: EntityID<UUID>) : UUIDEntity(id) {
  companion object : UUIDEntityClass<Employee>(Employees)

  var businessId by Employees.businessId
  var ssn by Employees.ssn
  var firstName by Employees.firstName
  var lastName by Employees.lastName
  var dateOfBirth by Employees.dateOfBirth
}
