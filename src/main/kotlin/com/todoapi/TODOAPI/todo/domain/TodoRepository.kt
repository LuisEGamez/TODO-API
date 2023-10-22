package com.todoapi.TODOAPI.todo.domain

interface TodoRepository {

    fun save(todo: Todo)

    fun find(id: TodoId): Todo?
}