package com.example.tracker.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(foreignKeys = [ForeignKey(
    entity = Pet::class,
    parentColumns = ["id"],
    childColumns = ["petId"],
    onDelete = ForeignKey.CASCADE
)],
    indices = [Index(value = ["petId"])])
data class Appointment(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val petId: Long,
    val title: String,
    val notes: String,
    val location: String,
    val datetime: LocalDateTime,
    val status: String,
)
