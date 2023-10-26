package com.todoapi.todo.application.create

import com.todoapi.shared.BaseTest
import com.todoapi.todo.domain.TodoRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TodoCreatorTest : BaseTest() {

  private lateinit var todoRepository: TodoRepository
  private lateinit var todoCreator: TodoCreator

  @BeforeEach
  fun setUp() {
    todoRepository = mockk(relaxUnitFun = true)
    clock = mockk()
    todoCreator = TodoCreator(todoRepository, clock)
  }

  @Test
  fun `should create a todo`() {

    givenFixedDate(fixedDate)

    val todo = todoCreator
      .create(
        title,
        description
      )

    assertEquals(title, todo.title.value)
    assertEquals(description, todo.description.value)


    thenTheTodoShouldBeSaved()

  }

  private fun thenTheTodoShouldBeSaved() {
    verify {
      todoRepository.save(
        any()
      )
    }
  }

  companion object {
    private const val title = "Kotlin Hexagonal Architecture"
    private const val description = "This is a description"
    private val fixedDate = LocalDateTime.parse("2022-08-09T14:50:42")
  }
}