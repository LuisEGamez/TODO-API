package com.todoapi.shared

import com.todoapi.TodoApiApplication
import com.todoapi.shared.security.SecurityConfigTest
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.PostgreSQLContainer


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = [TodoApiApplication::class, SecurityConfigTest::class])
@ActiveProfiles(value = ["test"])
class BaseIntegrationTest {

  init {
    postgresContainer.start()
  }

  companion object {
    @JvmStatic
    val postgresContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:14.5")
      .withDatabaseName("todo_database")
      .withUsername("postgres")
      .withPassword("postgres")
    //.withCopyToContainer(MountableFile.forClasspathResource("db/migrations"), "/docker-entrypoint-initdb.d")

  }


  @AfterEach
  fun tearDown() {
    unmockkAll()
  }

}