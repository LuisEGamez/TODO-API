package com.todoapi.todo.infrastructure

import com.todoapi.shared.Clock
import java.time.LocalDateTime

class ClockImp : Clock {
    override fun now(): LocalDateTime {
        return LocalDateTime.now()
    }
}
