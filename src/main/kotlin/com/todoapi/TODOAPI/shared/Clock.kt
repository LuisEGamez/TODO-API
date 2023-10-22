package com.todoapi.TODOAPI.shared

import java.time.LocalDateTime

interface Clock {
    fun now(): LocalDateTime
}
