package com.todoapi.role.infrastructure

import com.todoapi.role.domain.ERole
import com.todoapi.role.domain.Role
import com.todoapi.role.domain.RoleId
import com.todoapi.role.domain.RoleRepository
import com.todoapi.user.domain.User
import com.todoapi.user.domain.UserId
import com.todoapi.user.domain.UserRepository
import java.util.*


class JPAPostgresRoleRepository(private val jpaRoleRepository: JPARoleRepository) : RoleRepository {


  override fun save(role: Role) {


    val jpaEntity = RoleMapper.mapToJPAEntity(role)

    jpaRoleRepository.save(jpaEntity)

  }

  override fun findById(id: RoleId): Role? {
    TODO("Not yet implemented")

  }

  override fun findByRole(role: ERole): Role? {
    val roleJPAEntityOptional = jpaRoleRepository.findByRole(role)

    return RoleMapper.mapToDomainEntity(roleJPAEntityOptional)
  }

}