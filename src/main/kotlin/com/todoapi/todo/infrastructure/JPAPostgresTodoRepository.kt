package com.todoapi.todo.infrastructure

import com.todoapi.todo.domain.Todo
import com.todoapi.todo.domain.TodoId
import com.todoapi.todo.domain.TodoRepository


class JPAPostgresTodoRepository(private val hibernatePostgresTodoRepository : JPATodoRepository) : TodoRepository {


  override fun save(todo: Todo) {


    val jpaEntity = TodoMapper.mapToJPAEntity(todo)

    hibernatePostgresTodoRepository.save(jpaEntity)

  }

  override fun findById(id: TodoId): Todo? {
    TODO("Not yet implemented")

  }

}