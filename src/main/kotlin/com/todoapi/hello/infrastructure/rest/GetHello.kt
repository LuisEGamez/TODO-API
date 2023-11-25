package com.todoapi.hello.infrastructure.rest

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetHello {

  @GetMapping("/hello")
  fun hello(): String{
    return "Hello World Not Secured"
  }
  @GetMapping("/helloSecured")
  fun helloSecured(): String{
    return "Hello World Secured"
  }

  @GetMapping("/helloSecuredAdmin")
  @PreAuthorize("hasRole('ADMIN')")
  fun helloSecuredAdmin(): String{
    return "Hello World Secured Admin"
  }
  @GetMapping("/helloSecuredUser")
  @PreAuthorize("hasRole('USER')")
  fun helloSecuredUser(): String{
    return "Hello World Secured user"
  }

  @GetMapping("/helloSecuredBoth")
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  fun helloSecuredBoth(): String{
    return "Hello World Secured both"
  }
}