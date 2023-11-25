package com.todoapi.user.infrastructure.repository

import com.todoapi.user.domain.*


class JPAPostgresUserRepository(private val jpaUserRepository: JPAUserRepository) : UserRepository {


  override fun save(user: User) {


    val jpaEntity = UserMapper.mapToJPAEntity(user)
    jpaUserRepository.save(jpaEntity)

  }

  override fun findById(id: UserId): User? {

    val userJPAEntityOptional = jpaUserRepository.findById(id.value)
    return UserMapper.mapToDomainEntity(userJPAEntityOptional)

  }

  override fun findByEmail(userName: UserEmail): User? {
    val userJPAEntityOptional = jpaUserRepository.findByEmail(userName.value)
    return UserMapper.mapToDomainEntity(userJPAEntityOptional)
  }

}