package com.todoapi.user.infrastructure.repository

import com.todoapi.role.domain.Role
import com.todoapi.role.infrastructure.RoleMapper
import com.todoapi.user.domain.*
import java.util.*

class UserMapper {

  companion object {

    fun mapToJPAEntity(user: User): UserJPAEntity {


      return UserJPAEntity(
        user.id.value,
        user.createdAt,
        user.updatedAt,
        user.email.value,
        user.name.value,
        user.password.value,
        user.enabled.value,
        user.delete.value,
        user.roles.map { role: Role -> RoleMapper.mapToJPAEntity(role) }.toSet()
      )

    }

    fun mapToDomainEntity(optionalUserJPAEntity: Optional<UserJPAEntity>): User? {

      return optionalUserJPAEntity
        .orElse(null)
        ?.run {
          User(
            UserId(id),
            createdAt,
            updatedAt,
            UserEmail(email),
            UserName(name),
            UserPassword(password),
            roles.map { RoleMapper.mapToDomainEntity(it) }.toSet(),
            UserEnabled(enabled),
            UserDeleted(delete)
          )
        }

    }

  }


}