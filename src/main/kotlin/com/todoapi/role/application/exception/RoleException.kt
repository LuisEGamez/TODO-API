package com.todoapi.role.application.exception

sealed class RoleException (override val message: String, override val cause: Throwable? = null) : RuntimeException(message, cause)

data class RoleIdNotFoundException(val id: String) : RoleException("The role <$id> not found")
data class RoleNameNotFoundException(val name: String) : RoleException("The role with <$name> not found")
