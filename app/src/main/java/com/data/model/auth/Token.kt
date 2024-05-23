package com.data.model.auth

data class Token(
    val access_token: String,
    val token_type: String
)