package com.todoapi.todo.domain

interface TodoRepository {

    fun save(todo: Todo)

    fun findById(id: TodoId): Todo?
}