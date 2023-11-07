package com.todoapi.role.infrastructure

import com.todoapi.role.domain.ERole
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "roles")
data class RoleJPAEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  val id: UUID,
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, name = "role")
  val role: ERole,
)




