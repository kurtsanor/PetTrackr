package com.example.tracker.dto

data class SignUpRequest(
    val firstName: String,
    val surName: String,
    val email: String,
    val password: String
)
