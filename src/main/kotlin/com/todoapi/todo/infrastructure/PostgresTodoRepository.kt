package com.todoapi.todo.infrastructure

import com.todoapi.todo.domain.*
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.sql.ResultSet
import java.time.LocalDateTime
import java.util.*

class PostgresTodoRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) : TodoRepository {

  override fun save(todo: Todo) {

    MapSqlParameterSource()
      .addValue("id", todo.id.value)
      .addValue("title", todo.title.value)
      .addValue("createdAt", todo.createdAt)
      .addValue("updatedAt", todo.updatedAt)
      .addValue("startedAt", todo.startedAt)
      .addValue("finishedAt", todo.finishedAt)
      .addValue("description", todo.description.value)
      .addValue("deleted", todo.deleted.value)
      .let { params ->
        jdbcTemplate
          .update(
            "INSERT INTO todo (id, title, created_at, updated_at, started_at, finished_at, description, deleted) VALUES (:id,:title,:createdAt,:updatedAt,:startedAt,:finishedAt,:description,:deleted)",
            params
          )
      }


  }

  override fun find(id: TodoId): Todo? {
    TODO("Not yet implemented")
  }

  private fun mapRow(): RowMapper<Todo> {
    return RowMapper { rs: ResultSet, _: Int ->
      val id = TodoId(UUID.fromString(rs.getString("id")))
      val title = TodoTitle(rs.getString("title"))
      val createdAt = rs.getTimestamp("created_at").toLocalDateTime()
      val updatedAt = rs.getTimestamp("updated_at").toLocalDateTime()
      val startedAt: LocalDateTime? =  rs.getTimestamp("started_at").toLocalDateTime()?: null
      val finishedAt: LocalDateTime? = rs.getTimestamp("finished_at").toLocalDateTime()?: null
      val description = TodoDescription(rs.getString("description"))
      val deleted = TodoDeleted(rs.getBoolean("deleted"))
      Todo(id, title, createdAt, updatedAt, startedAt, finishedAt, description, deleted)
    }
  }
}