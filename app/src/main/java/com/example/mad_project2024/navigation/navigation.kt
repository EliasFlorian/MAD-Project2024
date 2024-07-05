package com.example.mad_project2024.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mad_project2024.screens.*
import com.example.mad_project2024.viewmodels.*

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.StartScreen.route) {
        composable(route = Screen.StartScreen.route) {
            StartScreen(navController)
        }
        composable(route = Screen.AuthScreen.route) {
            AuthScreen(navController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.TravelModeScreen.route + "/{countryCode}",
            arguments = listOf(navArgument("countryCode") { type = NavType.StringType })
        ) { backStackEntry ->
            val countryCode = backStackEntry.arguments?.getString("countryCode") ?: ""
            val viewModel: InformationViewModel = hiltViewModel()
            val authViewModel: AuthViewModel = hiltViewModel()
            TravelModeScreen(
                navController = navController,
                viewModel = viewModel,
                authViewModel = authViewModel,
                countryCode = countryCode
            )
        }
        composable(
            route = Screen.InteractionModeScreen.route + "/{countryCode}",
            arguments = listOf(navArgument("countryCode") { type = NavType.StringType })
        ) { backStackEntry ->
            val countryCode = backStackEntry.arguments?.getString("countryCode") ?: ""
            val viewModel: InformationViewModel = hiltViewModel()
            val authViewModel: AuthViewModel = hiltViewModel()
            InteractionModeScreen(
                navController = navController,
                viewModel = viewModel,
                authViewModel = authViewModel,
                countryCode = countryCode
            )
        }
        composable(
            route = Screen.SuggestionsScreen.route + "/{subcategory}",
            arguments = listOf(navArgument("subcategory") { type = NavType.StringType })
        ) { backStackEntry ->
            val subcategory = backStackEntry.arguments?.getString("subcategory") ?: ""
            val viewModel: SuggestionsViewModel = hiltViewModel()
            SuggestionsScreen(navController = navController, viewModel = viewModel, subcategory = subcategory)
        }
        composable(route = Screen.AccountScreen.route) {
            AccountScreen(navController)
        }
    }
}
