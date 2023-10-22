package com.todoapi.shared

import java.time.LocalDateTime

interface Clock {
    fun now(): LocalDateTime
}
