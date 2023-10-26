package com.todoapi.shared

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.MountableFile
import javax.sql.DataSource

@TestConfiguration
class TestPostgresConfig {

  init {
    postgresContainer.start()
  }

  companion object {
    @JvmStatic
    val postgresContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:14.5")
      .withDatabaseName("todo_database_test")
      .withUsername("test")
      .withPassword("test")
      .withCopyToContainer(MountableFile.forClasspathResource("db/migrations"), "/docker-entrypoint-initdb.d")

  }

  @Bean
  fun dataSource(): DataSource {
    return DataSourceBuilder
      .create()
      .url(postgresContainer.getJdbcUrl())
      .username(postgresContainer.username)
      .password(postgresContainer.password)
      .build()
  }
}
