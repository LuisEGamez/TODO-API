package com.todoapi.TODOAPI.todo.infrastructure

import com.todoapi.TODOAPI.shared.Clock
import java.time.LocalDateTime

class ClockImp : Clock {
    override fun now(): LocalDateTime {
        return LocalDateTime.now()
    }
}
