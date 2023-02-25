package io.craigmiller160

import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class PeopleRoutesTest {
  @Test
  fun testRoot() = testApplication {
    client.get("/people").apply { assertEquals(HttpStatusCode.OK, status) }
  }
}
