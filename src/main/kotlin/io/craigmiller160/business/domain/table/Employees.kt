package io.craigmiller160.business.domain.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.date

object Employees : UUIDTable("employees") {
  val businessId = uuid("business_id").references(Businesses.id)
  val ssn = char("ssn", 9)
  val firstName = varchar("first_name", 255)
  val lastName = varchar("last_name", 255)
  val dateOfBirth = date("date_of_birth")
}
