package com.todoapi.todo.application.create

import com.todoapi.shared.Clock
import com.todoapi.todo.domain.TodoRepository

class TodoCreator(private val todoRepository: TodoRepository, private val clock: Clock) {

    fun create(
        title: String,
        description: String
        ) {

    }
}