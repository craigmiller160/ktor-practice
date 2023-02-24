package io.craigmiller160.people

import java.util.UUID
import kotlinx.serialization.Serializable

@Serializable data class OldPerson(val name: String, val age: Int)

data class Person(val id: UUID, val name: String, val age: Int)

data class PersonRequest(val name: String, val age: Int)
