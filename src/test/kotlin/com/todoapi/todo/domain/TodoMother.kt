package com.todoapi.todo.domain

import java.time.LocalDateTime
import java.util.UUID

object TodoMother {

  fun sample(
    id: UUID = UUID.fromString("ce30c1ad-bcf9-47f3-9228-fca9ab081f57"),
    title: String = "This is a title",
    createdAt: LocalDateTime = LocalDateTime.parse("2022-08-31T09:00:00"),
    updatedAt: LocalDateTime = LocalDateTime.parse("2022-08-31T09:00:00"),
    startedAt: LocalDateTime? = null,
    finishedAt: LocalDateTime? = null,
    description: String = "A simple description",
    deleted: Boolean = false,
  ) = Todo(
    TodoId(id),
    TodoTitle(title),
    createdAt,
    updatedAt,
    startedAt,
    finishedAt,
    TodoDescription(description),
    TodoDeleted(deleted)
  )
}