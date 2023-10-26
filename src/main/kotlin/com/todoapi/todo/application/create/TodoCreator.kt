package com.todoapi.todo.application.create

import com.todoapi.shared.Clock
import com.todoapi.todo.domain.Todo
import com.todoapi.todo.domain.TodoRepository
import java.util.UUID

class TodoCreator(private val todoRepository: TodoRepository, private val clock: Clock) {

  fun create(
    title: String,
    description: String
  ): Todo {
    Todo
      .create(
        title,
        clock.now(),
        clock.now(),
        null,
        null,
        description
      ).let {
        todoRepository.save(it)
        return it
      }
  }
}