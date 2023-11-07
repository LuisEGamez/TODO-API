package com.todoapi.todo.infrastructure

import com.todoapi.todo.domain.*

class TodoMapper {

  companion object{

    fun mapToJPAEntity(todo: Todo): TodoJPAEntity{

      return TodoJPAEntity(
        todo.id.value,
        todo.title.value,
        todo.createdAt,
        todo.updatedAt,
        todo.startedAt,
        todo.finishedAt,
        todo.description.value,
        todo.deleted.value
      )

    }

    fun mapToDomainEntity(todo: TodoJPAEntity): Todo{

      return Todo(
        TodoId(todo.id),
        TodoTitle(todo.title),
        todo.createdAt,
        todo.updatedAt,
        todo.startedAt,
        todo.finishedAt,
        TodoDescription(todo.description),
        TodoDeleted(todo.deleted)
      )

    }

  }


}