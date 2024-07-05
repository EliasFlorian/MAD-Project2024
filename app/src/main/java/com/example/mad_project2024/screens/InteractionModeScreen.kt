package com.example.mad_project2024.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mad_project2024.components.BottomBar
import com.example.mad_project2024.components.TopAppBar
import com.example.mad_project2024.components.SubCategoryCard
import com.example.mad_project2024.components.SubCategoryContentView
import com.example.mad_project2024.models.SubCategory
import com.example.mad_project2024.navigation.Screen
import com.example.mad_project2024.viewmodels.InformationViewModel
import com.example.mad_project2024.viewmodels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InteractionModeScreen(
    navController: NavController,
    viewModel: InformationViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
    countryCode: String
) {
    val informationState by viewModel.informationState.collectAsState()
    val authState by authViewModel.authState.collectAsState()
    var selectedSubCategory by remember { mutableStateOf<SubCategory?>(null) }

    LaunchedEffect(countryCode) {
        Log.d("InteractionModeScreen", "Fetching information for country: $countryCode")
        viewModel.fetchInformation(countryCode)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = selectedSubCategory?.title ?: "Interaction") }

            )
        },
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        informationState.information?.let { information ->
            val communicationCategory = information.categories.find { it.title == "Communication" }
            communicationCategory?.let { category ->
                if (selectedSubCategory == null) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(category.subCategories) { subCategory ->
                            SubCategoryCard(navController, subCategory) {
                                selectedSubCategory = it
                            }
                        }
                    }
                } else {
                    SubCategoryContentView(
                        subCategory = selectedSubCategory!!,
                        isGuest = authState.isGuest,
                        navController = navController
                    )
                }
            }
        }
    }
}
