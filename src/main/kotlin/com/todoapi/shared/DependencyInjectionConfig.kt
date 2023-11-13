package com.todoapi.shared

import com.todoapi.role.domain.RoleRepository
import com.todoapi.todo.application.create.TodoCreator
import com.todoapi.todo.domain.TodoRepository
import com.todoapi.todo.infrastructure.ClockImp
import com.todoapi.user.application.UserCreateUseCase
import com.todoapi.user.domain.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyInjectionConfig {

    @Bean
    fun clock() = ClockImp()

    @Bean
    fun todoCreator(todoRepository: TodoRepository, clock: Clock) = TodoCreator(todoRepository, clock)

    @Bean
    fun userCreateUserCase(userRepository: UserRepository, roleRepository: RoleRepository,  clock: Clock) = UserCreateUseCase(userRepository,roleRepository, clock)

}