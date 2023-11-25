package com.todoapi.user.domain

interface UserRepository {

    fun save(user: User)

    fun findById(id: UserId): User?

    fun findByEmail(userName: UserEmail): User?
}