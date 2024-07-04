package com.example.mad_project2024.navigation

sealed class Screen(val route: String) {
    object AuthScreen: Screen(route = "auth")
    object HomeScreen: Screen(route = "home")
    object MainCategoryScreen : Screen("main_category/{countryCode}/{mode}") {
        fun createRoute(countryCode: String, mode: String) = "main_category/$countryCode/$mode"
    }
    object SubCategoryScreen: Screen(route = "sub_category")
    object AccountScreen: Screen(route = "account")
    object SuggestionsScreen : Screen("suggestions/{subcategory}") {
        fun createRoute(subcategory: String) = "suggestions/$subcategory"
    }

    object StartScreen : Screen("start")
}