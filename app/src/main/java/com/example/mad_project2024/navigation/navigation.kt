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
import com.example.mad_project2024.screens.SuggestionsScreen
import com.example.mad_project2024.viewmodels.AuthViewModel
import com.example.mad_project2024.viewmodels.InformationViewModel
import com.example.mad_project2024.viewmodels.ModeViewModel
import com.example.mad_project2024.viewmodels.SuggestionsViewModel

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
            route = Screen.MainCategoryScreen.route + "/{mode}/{countryCode}",
            arguments = listOf(
                navArgument("mode") { type = NavType.StringType },
                navArgument("countryCode") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val mode = backStackEntry.arguments?.getString("mode") ?: "travel"
            val countryCode = backStackEntry.arguments?.getString("countryCode") ?: ""
            MainCategoryScreen(navController = navController, viewModel = hiltViewModel(), authViewModel = hiltViewModel(), countryCode = countryCode, mode = mode)
        }


        composable(
            route = Screen.AccountScreen.route
        ) {
            AccountScreen(navController)
        }
        composable(
            route = Screen.SuggestionsScreen.route,
            arguments = listOf(
                navArgument("subcategory") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val subcategory = backStackEntry.arguments?.getString("subcategory") ?: ""
            val suggestionsViewModel: SuggestionsViewModel = hiltViewModel()
            SuggestionsScreen(navController, suggestionsViewModel, subcategory)
        }


    }
}