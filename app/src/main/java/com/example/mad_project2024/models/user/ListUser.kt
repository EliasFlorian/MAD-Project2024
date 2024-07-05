package com.example.mad_project2024.models.user

data class ListUser(
    val id: Int,
    val nickName: String,
    val eMail: String,
    val displayedName: String,
    val role: String,
    val homeCountry: String
)