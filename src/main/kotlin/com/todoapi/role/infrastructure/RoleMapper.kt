package com.todoapi.role.infrastructure

import com.todoapi.role.domain.Role
import com.todoapi.role.domain.RoleId

class RoleMapper {

  companion object {

    fun mapToJPAEntity(role: Role): RoleJPAEntity {

      return RoleJPAEntity(
        role.id.value,
        role.role
      )

    }

    fun mapToDomainEntity(roleJPAEntity: RoleJPAEntity): Role {

      return Role(
        RoleId(roleJPAEntity.id),
        roleJPAEntity.role
      )

    }

  }


}