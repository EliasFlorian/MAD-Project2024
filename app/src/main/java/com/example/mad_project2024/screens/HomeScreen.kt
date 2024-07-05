package com.example.mad_project2024.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mad_project2024.components.BottomBar
import com.example.mad_project2024.components.InteractionModeCard
import com.example.mad_project2024.components.TopAppBar
import com.example.mad_project2024.components.TravelModeCard
import com.example.mad_project2024.models.Country
import com.example.mad_project2024.viewmodels.AuthViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, viewModel: AuthViewModel = hiltViewModel()) {
    val authState by viewModel.authState.collectAsState()
    val countries by viewModel.countries.collectAsState(emptyList())
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf<Country?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = "Select Mode and Country", navController)
        },
        bottomBar = {
            BottomBar(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TravelModeCard(
                title = "Travel Mode",
                description = "Select this mode for travel information",
                navController = navController,
                selectedCountry = selectedCountry
            )
            Spacer(Modifier.size(64.dp))
            InteractionModeCard(
                title = "Interaction Mode",
                description = "Select this mode for general information",
                navController = navController,
                selectedCountry = selectedCountry
            )
            Spacer(Modifier.size(64.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = selectedCountry?.country_name ?: "Select Country",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isDropdownExpanded = true },
                    label = { Text("Country") },
                    readOnly = true
                )
                DropdownMenu(
                    expanded = isDropdownExpanded,
                    onDismissRequest = { isDropdownExpanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    countries.forEach { country ->
                        DropdownMenuItem(
                            text = { Text(text = country.country_name) },
                            onClick = {
                                selectedCountry = country
                                viewModel.onCountryChange(selectedCountry!!.code)
                                isDropdownExpanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}
