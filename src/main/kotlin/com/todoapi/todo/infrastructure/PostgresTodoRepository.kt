package com.todoapi.todo.infrastructure

import com.todoapi.todo.domain.Todo
import com.todoapi.todo.domain.TodoId
import com.todoapi.todo.domain.TodoRepository
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class PostgresTodoRepository (jdbcTemplate: NamedParameterJdbcTemplate) : TodoRepository {

    override fun save(todo: Todo) {
        TODO("Not yet implemented")
    }

    override fun find(id: TodoId): Todo? {
        TODO("Not yet implemented")
    }
}