package com.example.mad_project2024.models.user

data class CreateUser(
    val nickName: String,
    val email: String,
    val displayedName: String,
    val password: String,
    val country: String
)