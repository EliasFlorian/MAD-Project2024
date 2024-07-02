package com.example.mad_project2024.models


data class SubCategory(
    val title: String,
    val description: String,
    val data: List<ContentData>
)


data class ContentData(
    val content: String,
    val rating: Int,
    val rateCount: Int
)

data class MainCategory(
    val title: String,
    val description: String,
    val subCategories: List<SubCategory>
)