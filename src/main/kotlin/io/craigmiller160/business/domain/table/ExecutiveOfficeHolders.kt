package io.craigmiller160.business.domain.table

import io.craigmiller160.business.domain.table.ExecutiveOffices.references
import org.jetbrains.exposed.dao.id.UUIDTable

object ExecutiveOfficeHolders : UUIDTable("executive_office_holders") {
  val businessId = uuid("business_id").references(Businesses.id)
  val employeeId = uuid("employee_id").references(Employees.id)
  val executiveOfficeId = uuid("executive_office_id").references(ExecutiveOffices.id)
}
