package com.todoapi.todo.infrastructure

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "todo")
data class TodoJPAEntity(
  @Id
  //@GeneratedValue(strategy = GenerationType.UUID)
  val id: UUID,
  @Column(nullable = false, name = "title")
  val title: String,
  @Column(nullable = false, name = "created_at")
  val createdAt: LocalDateTime,
  @Column(nullable = false, name = "updated_at")
  val updatedAt: LocalDateTime,
  @Column(nullable = true, name = "started_at")
  val startedAt: LocalDateTime?,
  @Column(nullable = true, name = "finished_at")
  val finishedAt: LocalDateTime?,
  @Column(nullable = false, name = "description")
  val description: String,
  @Column(nullable = false, name = "deleted")
  val deleted: Boolean
)


