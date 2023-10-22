package com.todoapi.TODOAPI.todo.domain

sealed class TodoException (override val message: String, override val cause: Throwable? = null) : RuntimeException(message, cause)

data class InvalidTodoIdException(val id: String, override val cause: Throwable?) : TodoException("The id <$id> is not a valid todo id", cause)
data class InvalidTodoTitleException(val title: String) : TodoException("The title <$title> is not a valid todo title")
data class InvalidTodoDescriptionException(val description: String) : TodoException("The description <$description> is not a valid todo description")