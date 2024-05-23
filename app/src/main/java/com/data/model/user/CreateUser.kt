package com.data.model.user

data class CreateUser(
    val nickName: String,
    val email: String,
    val displayedName: String,
    val password: String
)