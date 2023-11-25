package com.todoapi.user.infrastructure.rest.create

import com.todoapi.role.application.exception.RoleNameNotFoundException
import com.todoapi.user.application.UserCreateUseCase
import com.todoapi.user.domain.InvalidPasswordException
import com.todoapi.user.domain.InvalidUserEmailException
import com.todoapi.user.domain.InvalidUserNameException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class PostCreateUserController(private val userCreateUseCase: UserCreateUseCase, private val passwordEncoder: PasswordEncoder) {

  @PostMapping("/users")
  fun execute(
    @RequestBody request: CreateUserRequest
  ): ResponseEntity<String>{

    return try {
      val user = userCreateUseCase.create(request.email, request.name, passwordEncoder.encode(request.password))
      ResponseEntity.created(URI.create("/v1/users/${user.id.value}")).build()
    }catch (exception: Throwable){
      when (exception){
        is InvalidUserNameException -> ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("The course id is not valid")

        is InvalidUserEmailException -> ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("The course id is not valid")

        is InvalidPasswordException -> ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("The course id is not valid")

        is RoleNameNotFoundException -> ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(exception.message)

        else -> ResponseEntity
          .status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(exception.message.toString())

      }
    }

  }

}

data class CreateUserRequest(
  val email: String = "",
  val name: String = "",
  val password: String = ""
)