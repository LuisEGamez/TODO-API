package com.todoapi.user.doamin

import com.todoapi.role.domain.ERole
import com.todoapi.role.domain.Role
import com.todoapi.role.domain.RoleMother
import com.todoapi.user.domain.*
import java.time.LocalDateTime
import java.util.*

object UserObjectMother {

  fun sample(
    id: UUID = UUID.fromString("ce30c1ad-bcf9-47f3-9228-fca9ab081f57"),
    role: Role = Role.create(ERole.USER),
    createdAt: LocalDateTime = LocalDateTime.parse("2022-08-31T09:00:00"),
    updatedAt: LocalDateTime = LocalDateTime.parse("2022-08-31T09:00:00"),
    email: String = "email@test.com",
    name: String = "user name",
    password: String = "passwordTest",
    enabled: Boolean = true,
    delete: Boolean = false,
  ): User = User(
    UserId(id),
    createdAt,
    updatedAt,
    UserEmail(email),
    UserName(name),
    UserPassword(password),
    setOf(role),
    UserEnabled(enabled),
    UserDeleted(delete)
  )
}