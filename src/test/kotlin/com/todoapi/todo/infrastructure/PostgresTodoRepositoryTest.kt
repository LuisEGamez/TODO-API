package com.todoapi.todo.infrastructure

import com.todoapi.shared.BaseIntegrationTest
import com.todoapi.todo.domain.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.time.LocalDateTime
import java.util.*


class PostgresTodoRepositoryTest : BaseIntegrationTest() {

  @Autowired
  private lateinit var jdbcTemplate: JPATodoRepository

  @Autowired
  private lateinit var repository: JPAPostgresTodoRepository

  @Test
  fun `should save a todo`() {

    repository.save(todoToSave)

    val allTodos = jdbcTemplate.findAll()

    println(allTodos)

  }

  private fun mapRow(): RowMapper<Todo> {
    return RowMapper { rs: ResultSet, _: Int ->
      val id = TodoId(UUID.fromString(rs.getString("id")))
      val title = TodoTitle(rs.getString("title"))
      val createdAt = rs.getTimestamp("created_at").toLocalDateTime()
      val updatedAt = rs.getTimestamp("updated_at").toLocalDateTime()
      val startedAt: LocalDateTime? = rs.getTimestamp("started_at")?.toLocalDateTime()
      val finishedAt: LocalDateTime? = rs.getTimestamp("finished_at")?.toLocalDateTime()
      val description = TodoDescription(rs.getString("description"))
      val deleted = TodoDeleted(rs.getBoolean("deleted"))
      Todo(id, title, createdAt, updatedAt, startedAt, finishedAt, description, deleted)
    }
  }

  companion object {
    val todoId = UUID.fromString("13590efb-c181-4c5f-9f95-b768abde13e2")
    private val todoToSave = TodoMother.sample(todoId)
  }
}