package com.todoapi.user.infrastructure

import com.todoapi.role.infrastructure.RoleJPAEntity
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*
import java.util.regex.Pattern

@Entity
@Table(name = "users")
data class UserJPAEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  val id: UUID,
  @Column(nullable = false, name = "created_at")
  val createdAt: LocalDateTime,
  @Column(nullable = false, name = "updated_at")
  val updatedAt: LocalDateTime,
  @Column(nullable = false, name = "email")
  val email: String,
  @Column(nullable = false, name = "name")
  val name: String,
  @Column(nullable = false, name = "password")
  val password: String,
  @Column(nullable = false, name = "enabled")
  val enabled: Boolean,
  @Column(nullable = false, name = "delete")
  val delete: Boolean,
  @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleJPAEntity::class, cascade = [CascadeType.PERSIST])
  @JoinTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")], inverseJoinColumns = [JoinColumn(name = "role_id")])
  val roles: Set<RoleJPAEntity>
)


