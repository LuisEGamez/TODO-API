package com.todoapi.role.infrastructure

import com.todoapi.role.domain.Role
import com.todoapi.role.domain.RoleId
import java.util.Optional

class RoleMapper {

  companion object {

    fun mapToJPAEntity(role: Role): RoleJPAEntity {

      return RoleJPAEntity(
        role.id.value,
        role.role
      )

    }

    fun mapToDomainEntity(optionalRoleJPAEntity: Optional<RoleJPAEntity>): Role? {

      if(optionalRoleJPAEntity.isEmpty){
        return null
      }

      val roleJPAEntity = optionalRoleJPAEntity.get()

      return Role(
        RoleId(roleJPAEntity.id),
        roleJPAEntity.role
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