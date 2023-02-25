package io.craigmiller160.people.dto

import io.craigmiller160.serializers.UUIDAsString
import kotlinx.serialization.Serializable

@Serializable data class PersonResponse(val id: UUIDAsString, val name: String, val age: Int)
