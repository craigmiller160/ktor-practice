package io.craigmiller160

import io.craigmiller160.people.domain.entity.Person
import io.craigmiller160.people.domain.table.People
import io.craigmiller160.people.dto.PersonResponse
import io.craigmiller160.testcontainers.common.TestcontainersExtension
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.testing.TestApplication
import io.ktor.test.dispatcher.testSuspend
import kotlin.test.assertEquals
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestcontainersExtension::class)
class PeopleRoutesTest {
  companion object {
    lateinit var app: TestApplication

    @JvmStatic
    @BeforeAll
    fun beforeAll() {
      app =
          TestApplication {

            //        install(ClientContentNegotiation) {
            //
            //        }
          }
      app.start()
    }

    @JvmStatic
    @AfterAll
    fun afterAll() {
      app.stop()
    }
  }

  @BeforeEach
  fun setup() {
    transaction {
      repeat(3) { index ->
        Person.new {
          name = "Person_$index"
          age = 50 + index
        }
      }
    }
  }

  @AfterEach
  fun cleanup() {
    transaction { People.deleteAll() }
  }

  @Test
  fun testRoot() = testSuspend {
    app.client.get("/people").apply {
      assertEquals(HttpStatusCode.OK, status)
      val body = body<PersonResponse>()
      println(body)
    }
  }
}
