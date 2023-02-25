package io.craigmiller160.business.dto

import io.craigmiller160.serializers.UUIDAsString
import kotlinx.serialization.Serializable

@Serializable
data class BusinessResponse(
    val id: UUIDAsString,
    val ein: String,
    val name: String,
    val executives: List<ExecutiveShortResponse>,
    val departments: List<DepartmentShortResponse>
)
