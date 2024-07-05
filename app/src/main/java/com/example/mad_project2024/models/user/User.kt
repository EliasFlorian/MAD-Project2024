package com.example.mad_project2024.models.user

import com.example.mad_project2024.models.Country

data class User(
    val nickName: String,
    val eMail: String,
    val displayedName: String,
    val role: String,
    val homeCountry: String
)