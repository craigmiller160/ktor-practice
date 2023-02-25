package io.craigmiller160.business.domain.entity

import io.craigmiller160.business.domain.table.Businesses
import java.util.UUID
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.id.EntityID

class Business(id: EntityID<UUID>) : UUIDEntity(id) {
  companion object : EntityClass<UUID, Business>(Businesses)

  var ein by Businesses.ein
  var name by Businesses.name
}
