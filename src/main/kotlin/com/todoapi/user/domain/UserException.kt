package com.todoapi.user.domain

sealed class UserException (override val message: String, override val cause: Throwable? = null) : RuntimeException(message, cause)

data class InvalidUserIdException(val id: String, override val cause: Throwable?) : UserException("The id <$id> is not a valid todo id", cause)
data class InvalidUserEmailException(val email: String) : UserException("The email <$email> is not a valid email")
data class InvalidUserNameException(val name: String) : UserException("The user name <$name> is not a valid user name")
