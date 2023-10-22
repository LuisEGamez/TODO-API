package com.todoapi.shared

import com.todoapi.todo.infrastructure.PostgresTodoRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Configuration
class DataBaseConfig {

    @Bean
    fun todoRepository(jdbcTemplate: NamedParameterJdbcTemplate) = PostgresTodoRepository(jdbcTemplate)
}