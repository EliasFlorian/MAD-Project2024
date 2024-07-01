package com.example.mad_project2024.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mad_project2024.screens.AccountScreen
import com.example.mad_project2024.screens.AuthScreen
import com.example.mad_project2024.screens.HomeScreen
import com.example.mad_project2024.screens.MainCategoryScreen
import com.example.mad_project2024.screens.StartScreen
import com.example.mad_project2024.screens.SubCategoryScreen
import com.example.mad_project2024.screens.SuggestionsScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = Screen.StartScreen.route) {

        composable(
            route = Screen.StartScreen.route
        ) {
            StartScreen(navController)
        }
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
            MainCategoryScreen(navController)
        }
        composable(
            route = Screen.SubCategoryScreen.route
        ) {
            SubCategoryScreen(navController)
        }
        composable(
            route = Screen.AccountScreen.route
        ) {
            AccountScreen(navController)
        }
        composable(
            route = "${Screen.SuggestionsScreen.route}/{subcategory}",
            arguments = listOf(navArgument("subcategory") { type = NavType.StringType })
        ) { backStackEntry ->
            val subcategory = backStackEntry.arguments?.getString("subcategory") ?: ""
            SuggestionsScreen(navController = navController, viewModel = hiltViewModel(), subcategory = subcategory)
        }


    }
}