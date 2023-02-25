package io.craigmiller160.business.dto

import io.craigmiller160.serializers.UUIDAsString
import kotlinx.serialization.Serializable

@Serializable
data class DepartmentShortResponse(
    val id: UUIDAsString,
    val name: String,
    val managerId: UUIDAsString,
    val managerName: String,
    val employeeCount: Int
)
