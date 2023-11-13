package com.todoapi.user.infrastructure.repository

import org.springframework.data.repository.CrudRepository
import java.util.*

interface JPAUserRepository : CrudRepository<UserJPAEntity, UUID> {

  fun findByEmail(email: String): Optional<UserJPAEntity>
}
