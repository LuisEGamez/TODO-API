package com.todoapi.user.application.exception

sealed class UserException (override val message: String, override val cause: Throwable? = null) : RuntimeException(message, cause)

data class UserNotFoundException(val id: String) : UserException("The user <$id> not found")
