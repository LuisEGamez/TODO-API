package com.todoapi.shared

import com.todoapi.todo.infrastructure.JPATodoRepository
import com.todoapi.todo.infrastructure.JPAPostgresTodoRepository
import com.todoapi.user.infrastructure.JPAPostgresUserRepository
import com.todoapi.user.infrastructure.JPAUserRepository
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
}