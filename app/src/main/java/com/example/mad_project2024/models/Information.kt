package com.example.mad_project2024.models

data class InformationResponse(
    val country: Country,
    val categories: List<Category>
)

data class Category(
    val title: String,
    val description: String,
    val subCategories: List<SubCategory>
)

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