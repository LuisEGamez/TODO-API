package com.todoapi.role.infrastructure

import com.todoapi.role.domain.ERole
import org.springframework.data.repository.CrudRepository
import java.util.*


interface JPARoleRepository : CrudRepository<RoleJPAEntity, UUID>{
  fun findByRole(id: ERole): Optional<RoleJPAEntity>
}
