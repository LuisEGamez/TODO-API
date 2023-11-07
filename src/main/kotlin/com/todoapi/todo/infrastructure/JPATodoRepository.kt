package com.todoapi.todo.infrastructure

import org.springframework.data.repository.CrudRepository
import java.util.*

interface JPATodoRepository : CrudRepository<TodoJPAEntity, UUID> {


}
