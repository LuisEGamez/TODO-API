package com.todoapi.shared

import com.todoapi.role.infrastructure.JPAPostgresRoleRepository
import com.todoapi.role.infrastructure.JPARoleRepository
import com.todoapi.todo.infrastructure.JPATodoRepository
import com.todoapi.todo.infrastructure.JPAPostgresTodoRepository
import com.todoapi.user.infrastructure.repository.JPAPostgresUserRepository
import com.todoapi.user.infrastructure.repository.JPAUserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataBaseConfig {

//    @Bean
//    fun todoRepository(jdbcTemplate: NamedParameterJdbcTemplate) = PostgresTodoRepository(jdbcTemplate)

    @Bean
    fun todoRepository(jpaTodoRepository: JPATodoRepository) = JPAPostgresTodoRepository(jpaTodoRepository)

    @Bean
    fun userRepository(jpaUserRepository: JPAUserRepository) = JPAPostgresUserRepository(jpaUserRepository)

    @Bean
    fun roleRepository(jpaUserRepository: JPARoleRepository) = JPAPostgresRoleRepository(jpaUserRepository)
}