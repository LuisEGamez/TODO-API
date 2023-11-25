package com.todoapi.shared.security.filters

import com.todoapi.shared.security.UserDetailsServiceImp
import com.todoapi.shared.security.jwt.JwtUtils
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthorizationFilter(
  private val jwtUtils: JwtUtils,
  private val userDetailsServiceImp: UserDetailsServiceImp
) : OncePerRequestFilter(){

  override fun doFilterInternal(
    request: HttpServletRequest,
    response: HttpServletResponse,
    filterChain: FilterChain
  ) {
    val tokenHeader = request.getHeader("Authorization")

    if (tokenHeader!= null && tokenHeader.startsWith("Bearer ")){

      val token = tokenHeader.substring(7, tokenHeader.length)

      if (jwtUtils.isTokenValid(token)){
        val email = jwtUtils.getUserName(token)
        val userDetails = userDetailsServiceImp.loadUserByUsername(email)

        val authenticationToken =
          UsernamePasswordAuthenticationToken(userDetails.username, null, userDetails.authorities)

        SecurityContextHolder.getContext().authentication = authenticationToken

      }


    }

    filterChain.doFilter(request, response)
  }
}