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
        composable(route = Screen.HomeScreen.route) {
            val viewModel: ModeViewModel = hiltViewModel()
            HomeScreen(navController, viewModel)
        }
        composable(route = Screen.MainCategoryScreen.route + "/{countryCode}") { backStackEntry ->
            val countryCode = backStackEntry.arguments?.getString("countryCode") ?: ""
            val viewModel: InformationViewModel = hiltViewModel()
            MainCategoryScreen(navController = navController, viewModel = viewModel, countryCode = countryCode)
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

      //  composable(route = Screen.SubCategoryScreen.route + "/{countryCode}") { backStackEntry ->
    //        val countryCode = backStackEntry.arguments?.getString("countryCode") ?: ""
  //          val viewModel: InformationViewModel = hiltViewModel()
//            SubCategoryScreen(navController = navController, viewModel = viewModel, countryCode = countryCode)

        }

        composable(
            route = Screen.SuggestionsScreen.route + "/{subcategory}"
        ) { backStackEntry ->
            val subcategory = backStackEntry.arguments?.getString("subcategory") ?: ""
            val viewModel: SuggestionsViewModel = hiltViewModel()
            val navController = rememberNavController()
            SuggestionsScreen(navController = navController, viewModel = viewModel, subcategory = subcategory)
        }



    }
}