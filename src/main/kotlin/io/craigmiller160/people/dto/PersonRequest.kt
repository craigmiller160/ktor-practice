package io.craigmiller160.people.dto

import kotlinx.serialization.Serializable

@Serializable data class PersonRequest(val name: String, val age: Int)
