package com.example.tracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val firstName: String,
    val surName: String,
)
