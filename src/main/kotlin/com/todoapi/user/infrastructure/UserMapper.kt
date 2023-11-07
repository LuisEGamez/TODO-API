package com.todoapi.user.infrastructure

import com.todoapi.role.domain.Role
import com.todoapi.role.infrastructure.RoleJPAEntity
import com.todoapi.role.infrastructure.RoleMapper
import com.todoapi.user.domain.*

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

    fun mapToDomainEntity(userJPAEntity: UserJPAEntity): User {

      return User(
        UserId(userJPAEntity.id),
        userJPAEntity.createdAt,
        userJPAEntity.updatedAt,
        UserEmail(userJPAEntity.email),
        UserName(userJPAEntity.name),
        UserPassword(userJPAEntity.password),
        userJPAEntity.roles.map { roleJPAEntity: RoleJPAEntity -> RoleMapper.mapToDomainEntity(roleJPAEntity) }.toSet(),
        UserEnabled(userJPAEntity.enabled),
        UserDeleted(userJPAEntity.delete)
      )

    }

  }


}