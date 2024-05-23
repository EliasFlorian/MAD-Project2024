package com.data.model.auth

data class OAuth2PasswordRequest(
    val username: String,
    val password: String
)