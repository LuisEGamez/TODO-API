package com.todoapi.shared.security

import com.todoapi.shared.security.filters.JwtAuthenticationFilter
import com.todoapi.shared.security.filters.JwtAuthorizationFilter
import com.todoapi.shared.security.jwt.JwtUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig(
  private val userDetailsServiceImp: UserDetailsServiceImp,
  private val jwtUtils: JwtUtils,
  private val jwtAuthorizationFilter: JwtAuthorizationFilter
) {

  @Bean
  fun securityFilterChain(
    httpSecurity: HttpSecurity,
    authenticationManager: AuthenticationManager
  ): SecurityFilterChain {
    return httpSecurity
      .csrf { csrf -> csrf.disable() }
      .authorizeHttpRequests { auth ->
        run {
          auth.requestMatchers("/hello").permitAll()
          auth.anyRequest().authenticated()
        }
      }
      .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
      .authenticationProvider(authenticationProvider())
      .addFilter(JwtAuthenticationFilter(jwtUtils).apply { setAuthenticationManager(authenticationManager) })
      .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter::class.java)
      .build()


  }

  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }

  @Bean
  fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager =
    configuration.authenticationManager


  @Bean
  fun authenticationProvider(): DaoAuthenticationProvider {
    return DaoAuthenticationProvider()
      .apply {
        setUserDetailsService(userDetailsServiceImp)
        setPasswordEncoder(passwordEncoder())
      }
  }


  /*  @Bean
    fun userDetailsService():UserDetailsService{
      val manager = InMemoryUserDetailsManager()
        .apply { createUser(
          User
            .withUsername("luis")
            .password("1234")
            .roles()
            .build()
        ) }
      return manager
    }*/
}

