package io.craigmiller160

import io.craigmiller160.testcontainers.common.TestcontainersExtension
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.TestApplication
import io.ktor.test.dispatcher.testSuspend
import kotlin.test.assertEquals
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestcontainersExtension::class)
class PeopleRoutesTest {
  companion object {
    lateinit var app: TestApplication

    @JvmStatic
    @BeforeAll
    fun beforeAll() {
      app = TestApplication {}
      app.start()
    }

    @JvmStatic
    @AfterAll
    fun afterAll() {
      app.stop()
    }
  }
  @Test
  fun testRoot() = testSuspend {
    app.client.get("/people").apply { assertEquals(HttpStatusCode.OK, status) }
  }
}
