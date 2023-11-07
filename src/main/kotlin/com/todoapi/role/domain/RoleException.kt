package com.todoapi.role.domain

sealed class RoleException (override val message: String, override val cause: Throwable? = null) : RuntimeException(message, cause)

data class InvalidRoleIdException(val id: String, override val cause: Throwable?) : RoleException("The id <$id> is not a valid todo id", cause)
data class InvalidUserEmailException(val email: String) : RoleException("The email <$email> is not a valid email")
data class InvalidUserNameException(val name: String) : RoleException("The user name <$name> is not a valid user name")
