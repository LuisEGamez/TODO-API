package com.todoapi.user.infrastructure.rest

import com.todoapi.shared.BaseAcceptanceTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class PostCreateUserAcceptanceTestRestAssure : BaseAcceptanceTest() {

  @Autowired
  private lateinit var mockMvc: MockMvc

  @Test
  fun `should create a course successfully`() {
    mockMvc.perform(
      MockMvcRequestBuilders.post("/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(
          """
               {
                  "email": "test@test.com",
                  "name": "test",
                  "password": "12345"
              }    
           """.trimIndent()
        )
    ).andExpect(MockMvcResultMatchers.status().isCreated)
      .andExpect {
        assertEquals("", it.response.contentAsString)
      }
  }

}
