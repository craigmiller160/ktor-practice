package io.craigmiller160.business.domain.table

import io.craigmiller160.business.domain.table.ExecutiveOffices.references
import org.jetbrains.exposed.dao.id.UUIDTable

object DepartmentMembers : UUIDTable("department_members") {
  val businessId = uuid("business_id").references(Businesses.id)
  val employeeId = uuid("employee_id").references(Employees.id)
  val departmentId = uuid("department_id").references(Departments.id)
  val isManager = bool("is_manager")
}
