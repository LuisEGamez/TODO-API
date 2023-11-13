package com.todoapi.user.infrastructure.repository

import com.todoapi.role.domain.Role
import com.todoapi.role.infrastructure.RoleJPAEntity
import com.todoapi.role.infrastructure.RoleMapper
import com.todoapi.user.domain.*
import java.util.Optional

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

      if(optionalUserJPAEntity.isEmpty){
        return null
      }

      val userJPAEntity = optionalUserJPAEntity.get()

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