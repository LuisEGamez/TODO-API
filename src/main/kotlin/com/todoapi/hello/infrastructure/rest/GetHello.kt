package com.todoapi.hello.infrastructure.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetHello {

  @GetMapping("hello")
  fun hello(): String{
    return "Hello World Not Secured"
  }
  @GetMapping("helloSecured")
  fun helloSecured(): String{
    return "Hello World Secured"
  }
}