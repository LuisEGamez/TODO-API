package com.todoapi.shared.security.filters

import com.fasterxml.jackson.databind.ObjectMapper
import com.todoapi.shared.security.jwt.JwtUtils
import com.todoapi.user.infrastructure.repository.UserJPAEntity
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtAuthenticationFilter(private val jwtUtils: JwtUtils) : UsernamePasswordAuthenticationFilter() {


  override fun attemptAuthentication(
    request: HttpServletRequest,
    response: HttpServletResponse
  ): Authentication {

    val user: UserJPAEntity
    val userName: String
    val password: String

    try {
      user = ObjectMapper().readValue(request.inputStream, UserJPAEntity::class.java)
      userName = user.email
      password = user.password
    } catch (e: Exception) {
      throw RuntimeException(e)
    }

    val authenticationToken = UsernamePasswordAuthenticationToken(userName, password)

    return authenticationManager.authenticate(authenticationToken)
  }

  override fun successfulAuthentication(
    request: HttpServletRequest,
    response: HttpServletResponse,
    chain: FilterChain,
    authResult: Authentication
  ) {

    val userDetails = authResult.principal as User
    val token = userDetails.let { jwtUtils.generateAccessToken(it.username) }

    val map = mapOf(
      "token" to token,
      "Message" to "Success authentication",
      "Username" to userDetails.username
    )


    response.apply {
      addHeader("Authorization", "Bearer $token")
      status = HttpStatus.OK.value()
      contentType = MediaType.APPLICATION_JSON_VALUE
      writer.use { it.write(ObjectMapper().writeValueAsString(map)) }
      flushBuffer()
    }

    super.successfulAuthentication(request, response, chain, authResult)
  }
}