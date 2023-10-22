package com.todoapi.todo.domain

import java.time.LocalDateTime
import java.util.*

data class Todo(
    val id: TodoId,
    val title: TodoTitle,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val startedAt: LocalDateTime?,
    val finishedAt: LocalDateTime?,
    val description: TodoDescription,
    val deleted: TodoDeleted
) {

    companion object {

        fun create(
            id: String,
            title: String,
            createdAt: LocalDateTime,
            updatedAt: LocalDateTime,
            startedAt: LocalDateTime,
            finishedAt: LocalDateTime,
            description: String,
        ): Todo {

            return Todo(
                TodoId.fromString(id),
                TodoTitle(title),
                createdAt,
                updatedAt,
                startedAt,
                finishedAt,
                TodoDescription(description),
                TodoDeleted(false)
            )

        }
    }

}

data class TodoId(val value: UUID) {

    companion object {
        fun fromString(id: String) = try {
            TodoId(UUID.fromString(id))
        } catch (exception: Exception) {
            throw InvalidTodoIdException(id, exception)
        }
    }

}

data class TodoTitle(val value: String) {

    init {
        validate()
    }

    private fun validate() {
        if (value.length > 50) {
            throw InvalidTodoTitleException(value)
        }
    }

}

data class TodoDescription(val value: String) {
    init {
        validate()
    }

    private fun validate() {
        if (value.length > 150) {
            throw InvalidTodoDescriptionException(value)
        }
    }
}

data class TodoDeleted(val value: Boolean)
