package com.example.mad_project2024.navigation

sealed class Screen(val route: String) {
    object AuthScreen: Screen(route = "auth")
    object HomeScreen: Screen(route = "home")
    object DetailModeScreen: Screen(route = "detail_mode")
    object AccountScreen: Screen(route = "account")
}