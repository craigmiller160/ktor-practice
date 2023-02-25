package io.craigmiller160.business.dto

import io.craigmiller160.serializers.UUIDAsString
import kotlinx.serialization.Serializable

@Serializable
data class ExecutiveShortResponse(
    val id: UUIDAsString,
    val shortTitle: String,
    val employeeName: String
)
