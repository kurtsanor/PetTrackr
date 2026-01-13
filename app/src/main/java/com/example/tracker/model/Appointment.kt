package com.example.tracker.model

import java.time.LocalDate
import java.time.LocalDateTime

data class Appointment(
    val id: Long?,
    val petId: Long,
    val title: String,
    val notes: String,
    val datetime: LocalDateTime,
    val status: String,
)
