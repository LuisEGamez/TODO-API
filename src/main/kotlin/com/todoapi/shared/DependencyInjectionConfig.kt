package com.todoapi.shared

import com.todoapi.todo.application.create.TodoCreator
import com.todoapi.todo.domain.TodoRepository
import com.todoapi.todo.infrastructure.ClockImp
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyInjectionConfig {

    @Bean
    fun clock() = ClockImp()

    @Bean
    fun todoCreator(todoRepository: TodoRepository, clock: Clock) = TodoCreator(todoRepository, clock)

}