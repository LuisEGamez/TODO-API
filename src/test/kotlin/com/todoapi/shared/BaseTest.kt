package com.todoapi.shared

import io.mockk.every
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import java.time.LocalDateTime

open class BaseTest {

  protected lateinit var clock: Clock

  protected fun givenFixedDate(fixedDateTime: LocalDateTime){
    every { clock.now() } returns fixedDateTime
  }

  @AfterEach
  protected fun cleanMock() {
    unmockkAll()
  }
}