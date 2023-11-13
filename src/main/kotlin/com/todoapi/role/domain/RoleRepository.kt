package com.todoapi.role.domain

interface RoleRepository {

    fun save(role: Role)

    fun findById(id: RoleId): Role?
    fun findByRole(role: ERole): Role?
}