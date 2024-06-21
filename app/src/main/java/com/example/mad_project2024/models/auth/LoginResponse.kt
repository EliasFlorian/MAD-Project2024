package com.example.mad_project2024.models.auth

data class LoginResponse(
    val token: String,
    val userId: Int
)