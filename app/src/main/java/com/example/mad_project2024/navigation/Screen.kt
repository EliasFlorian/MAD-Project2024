package com.example.mad_project2024.navigation

sealed class Screen(val route: String) {
    object AuthScreen: Screen(route = "auth")
    object HomeScreen: Screen(route = "home")
    object MainCategoryScreen: Screen(route = "main_category")
    object SubCategoryScreen: Screen(route = "sub_category")
    object AccountScreen: Screen(route = "account")
    object SuggestionsScreen : Screen("suggestions")

    object StartScreen : Screen("start")
}