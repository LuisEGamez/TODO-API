package com.todoapi.role.domain

import com.todoapi.user.domain.*
import java.time.LocalDateTime
import java.util.*

object RoleMother {

  fun sampleUser(
    id: UUID = UUID.fromString("ce30c1ad-bcf9-47f3-9228-fca9ab081f57"),
    role: ERole = ERole.USER
  ): Role = Role(
    RoleId(id),
    role
  )
}