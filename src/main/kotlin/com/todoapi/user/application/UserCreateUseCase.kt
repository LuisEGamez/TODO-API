package com.todoapi.user.application

import com.todoapi.role.application.exception.RoleNameNotFoundException
import com.todoapi.role.domain.ERole
import com.todoapi.role.domain.Role
import com.todoapi.role.domain.RoleRepository
import com.todoapi.shared.Clock
import com.todoapi.user.application.exception.UserNotFoundException
import com.todoapi.user.domain.User
import com.todoapi.user.domain.UserRepository

class UserCreateUseCase(private val userRepository: UserRepository,private val roleRepository: RoleRepository,  private val clock: Clock) {

  fun create(email: String, name: String, password: String): User{

    val role = roleRepository.findByRole(ERole.USER) ?: throw RoleNameNotFoundException(ERole.USER.name)

    User
      .create(
        clock.now(),
        clock.now(),
        email,
        name,
        password,
        setOf(role)
      ).let {
        userRepository.save(it)
        return it
      }
  }

}