package com.todoapi.shared.security

import com.todoapi.role.domain.Role
import com.todoapi.user.domain.UserEmail
import com.todoapi.user.domain.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImp(private val userRepository: UserRepository): UserDetailsService {

  override fun loadUserByUsername(email: String): UserDetails {
    val user = userRepository.findByEmail(UserEmail(email)) ?: throw UsernameNotFoundException("User not found with email $email")


    return User(
      user.email.value,
      user.password.value,
      user.enabled.value,
      true,
      true,
      true,
      user.roles.map { role: Role -> SimpleGrantedAuthority("ROLE_${role.role.name}") }.toSet()
    )

  }
}