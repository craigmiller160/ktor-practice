package io.craigmiller160

import io.craigmiller160.testcontainers.common.TestcontainersExtension
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestcontainersExtension::class)
class PeopleRoutesTest {
  @Test
  fun testRoot() = testApplication {
    client.get("/people").apply { assertEquals(HttpStatusCode.OK, status) }
  }
}
