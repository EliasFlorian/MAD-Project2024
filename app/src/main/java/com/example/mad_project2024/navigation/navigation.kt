package com.example.mad_project2024.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mad_project2024.screens.AuthScreen
import com.example.mad_project2024.screens.HomeScreen
import com.example.mad_project2024.screens.MainCategoryScreen
import com.example.mad_project2024.screens.SubCategoryScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = Screen.AuthScreen.route) {
        composable(
            route = Screen.AuthScreen.route
        ) {
            AuthScreen(navController)
        }
        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.MainCategoryScreen.route
        ) {
            MainCategoryScreen()
        }
        composable(
            route = Screen.SubCategoryScreen.route
        ) {
            SubCategoryScreen()
        }
    }
}