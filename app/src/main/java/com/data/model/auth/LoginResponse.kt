package com.data.model.auth

data class LoginResponse(
    val token: String,
    val userId: Int
)