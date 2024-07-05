package com.example.mad_project2024.models

import com.example.mad_project2024.models.user.User

data class Suggestion(
    val approvedFrom: User?,
    val suggestingUser: User,
    val selectedCountry: String,
    val selectedSubCategory: String,
    val suggestedContent: SuggestedContent,
    val id: String
)



data class SuggestedContent(
    val content: String,
    val rating: Int,
    val rateCount: Int
)
