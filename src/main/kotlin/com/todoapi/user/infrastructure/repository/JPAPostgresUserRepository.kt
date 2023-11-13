package com.todoapi.user.infrastructure.repository

import com.todoapi.user.domain.User
import com.todoapi.user.domain.UserId
import com.todoapi.user.domain.UserRepository
import jakarta.transaction.Transactional
import java.util.Optional


class JPAPostgresUserRepository(private val jpaUserRepository: JPAUserRepository) : UserRepository {


  override fun save(user: User) {


    val jpaEntity = UserMapper.mapToJPAEntity(user)
    jpaUserRepository.save(jpaEntity)

  }

  override fun findById(id: UserId): User? {

    val userJPAEntityOptional = jpaUserRepository.findById(id.value)
    return UserMapper.mapToDomainEntity(userJPAEntityOptional)

  }

}