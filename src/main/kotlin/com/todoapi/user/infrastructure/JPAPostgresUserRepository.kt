package com.todoapi.user.infrastructure

import com.todoapi.user.domain.User
import com.todoapi.user.domain.UserId
import com.todoapi.user.domain.UserRepository


class JPAPostgresUserRepository(private val hibernatePostgresTodoRepository: JPAUserRepository) : UserRepository {


  override fun save(user: User) {


    val jpaEntity = UserMapper.mapToJPAEntity(user)

    hibernatePostgresTodoRepository.save(jpaEntity)

  }

  override fun findById(id: UserId): User? {
    TODO("Not yet implemented")

  }

}