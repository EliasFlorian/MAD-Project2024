package com.example.mad_project2024.navigation

sealed class Screen(val route: String) {
    object AuthScreen : Screen(route = "auth")
    object HomeScreen : Screen(route = "home")
    object TravelModeScreen : Screen(route = "travel_mode/{countryCode}") {
        fun createRoute(countryCode: String) = "travel_mode/$countryCode"
    }
    object InteractionModeScreen : Screen(route = "interaction_mode/{countryCode}") {
        fun createRoute(countryCode: String) = "interaction_mode/$countryCode"
    }
    object SuggestionsScreen : Screen(route = "suggestions/{subcategory}") {
        fun createRoute(subcategory: String) = "suggestions/$subcategory"
    }
    object AccountScreen : Screen(route = "account")
    object StartScreen : Screen(route = "start")
}
