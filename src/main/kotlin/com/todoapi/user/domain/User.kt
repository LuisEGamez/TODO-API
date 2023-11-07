package com.todoapi.user.domain

import com.todoapi.role.domain.ERole
import com.todoapi.role.domain.Role
import java.time.LocalDateTime
import java.util.*
import java.util.regex.Pattern

data class User(
  val id: UserId,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,
  val email: UserEmail,
  val name: UserName,
  val password: UserPassword,
  val roles: Set<Role>,
  val enabled: UserEnabled,
  val delete: UserDeleted
) {

  companion object {

    fun create(
      createdAt: LocalDateTime,
      updatedAt: LocalDateTime,
      email: String,
      name: String,
      password: String,
      roles: Set<Role>
    ): User {

      return User(
        UserId(UUID.randomUUID()),
        createdAt,
        updatedAt,
        UserEmail(email),
        UserName(name),
        UserPassword(password),
        roles,
        UserEnabled(),
        UserDeleted()
      )

    }
  }

}

data class UserPassword(val value: String) {

  init {
    validate()
  }

  private fun validate() {
    if (value.isBlank() || value.isEmpty()) {
      throw InvalidUserNameException(value)
    }
  }

}

data class UserId(val value: UUID) {

  companion object {
    fun fromString(id: String) = try {
      UserId(UUID.fromString(id))
    } catch (exception: Exception) {
      throw InvalidUserIdException(id, exception)
    }
  }

}

data class UserEmail(val value: String) {

  init {
    validate()
  }

  private fun validate() {
    val stringPattern =  "^[A-Za-z0-9+_.-]+@(.+)$"
    val pattern = Pattern.compile(stringPattern)

    val matcher = pattern.matcher(value)

    if (!matcher.matches()) {
      throw InvalidUserEmailException(value)
    }
  }

}

data class UserName(val value: String) {

  init {
    validate()
  }

  private fun validate() {
    if (value.isBlank() || value.isEmpty()) {
      throw InvalidUserNameException(value)
    }
  }

}

data class UserDeleted(val value: Boolean){
  constructor(): this(true)
}
data class UserEnabled(val value: Boolean){
  constructor(): this(false)
}


