package com.todoapi.todo.infrastructure.rest.create


import com.todoapi.todo.application.create.TodoCreator
import java.net.URI
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostCreateCourseController(private val todoCreator: TodoCreator) {

    @PostMapping("/todo")
    fun execute(
        @RequestBody request: CreateCourseRequest
    ): ResponseEntity<String> {
        return try {
            val todo = todoCreator.create(request.title, request.description)
            ResponseEntity.created(URI.create("/todo/${todo.id.value}")).build()
        } catch (exception: Throwable) {
            when (exception) {
                /*is InvalidCourseIdException -> ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The course id is not valid")

                is InvalidCourseNameException -> ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The course name is not valid")

                is InvalidCourseDescriptionException -> ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The course description is not valid")*/

                else -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build()
            }
        }
    }
}

data class CreateCourseRequest(
    val title: String = "",
    val description: String = ""
)
