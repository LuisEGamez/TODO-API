package com.todoapi.role.domain

interface RoleRepository {

    fun save(todo: Role)

    fun findById(id: RoleId): Role?
}