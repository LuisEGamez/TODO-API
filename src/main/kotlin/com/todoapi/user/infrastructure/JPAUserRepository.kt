package com.todoapi.user.infrastructure

import org.springframework.data.repository.CrudRepository
import java.util.*

interface JPAUserRepository : CrudRepository<UserJPAEntity, UUID> {


}
