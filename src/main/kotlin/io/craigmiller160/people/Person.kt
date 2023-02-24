package io.craigmiller160.people

import kotlinx.serialization.Serializable

@Serializable data class Person(val name: String, val age: Int)
