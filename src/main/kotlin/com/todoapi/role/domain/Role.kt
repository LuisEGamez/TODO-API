package com.todoapi.role.domain

import java.util.*

data class Role(
  val id: RoleId,
  val role: ERole,
) {

  companion object {

    fun create(
      role: ERole,
    ): Role {

      return Role(
        RoleId(UUID.randomUUID()),
        role
      )

    }
  }

}


data class RoleId(val value: UUID) {

  companion object {
    fun fromString(id: String) = try {
      RoleId(UUID.fromString(id))
    } catch (exception: Exception) {
      throw InvalidRoleIdException(id, exception)
    }
  }

}




