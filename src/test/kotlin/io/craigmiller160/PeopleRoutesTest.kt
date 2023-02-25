package io.craigmiller160

import io.craigmiller160.testcontainers.common.config.ContainerConfig
import io.craigmiller160.testcontainers.common.config.TestcontainersCommonConfig
import io.craigmiller160.testcontainers.common.core.TestcontainerInitializer
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class PeopleRoutesTest {
  companion object {
    init {
      val result =
          TestcontainerInitializer.initialize(
              TestcontainersCommonConfig(
                  postgres = ContainerConfig(enable = true),
                  keycloak = ContainerConfig(enable = false)))
      println("JDBC URL: ${result.postgresContainer?.jdbcUrl}")
    }
  }
  @Test
  fun testRoot() = testApplication {
    client.get("/people").apply { assertEquals(HttpStatusCode.OK, status) }
  }

  @Test
  fun foo() {
    println(System.getProperty("testcontainers.common.postgres.url"))
  }
}
