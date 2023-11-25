package com.todoapi.user.infrastructure.repository

import com.todoapi.role.domain.ERole
import com.todoapi.role.domain.RoleMother
import com.todoapi.role.infrastructure.JPAPostgresRoleRepository
import com.todoapi.shared.BaseIntegrationTest
import com.todoapi.user.doamin.UserObjectMother
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class JPAPostgresUserRepositoryTest : BaseIntegrationTest() {

  @Autowired
  private lateinit var jpaPostgresUserRepository: JPAPostgresUserRepository

  @Autowired
  private lateinit var jpaPostgresRoleRepository: JPAPostgresRoleRepository

  @Test
  fun `should save a user`() {

    val role = jpaPostgresRoleRepository.findByRole(ERole.USER) ?: RoleMother.sampleUser()

    val user = UserObjectMother.sample(userId, role)

    jpaPostgresUserRepository.save(user)

  }

  companion object {

    val userId: UUID = UUID.fromString("13590efb-c181-4c5f-9f95-b768abde13e2")
  }

}
