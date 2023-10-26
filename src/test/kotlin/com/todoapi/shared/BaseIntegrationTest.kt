package com.todoapi.shared

import com.todoapi.TodoApiApplication
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.MountableFile
import javax.sql.DataSource


@DataJpaTest
@SpringJUnitConfig(classes = [TestPostgresConfig::class])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class BaseIntegrationTest {




  @AfterEach
  fun tearDown() {
    unmockkAll()
  }

}