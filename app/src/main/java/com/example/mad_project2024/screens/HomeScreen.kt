package com.example.mad_project2024.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mad_project2024.components.BottomBar
import com.example.mad_project2024.models.Country
import com.example.mad_project2024.navigation.Screen
import com.example.mad_project2024.viewmodels.ModeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: ModeViewModel = hiltViewModel()) {
    val countries by viewModel.countries.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf<Country?>(null) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Select Mode and Country") }) },
        bottomBar = { BottomBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box {
                TextButton(onClick = { expanded = true }) {
                    Text(text = selectedCountry?.country_name ?: "Select Country")
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    countries.forEach { country ->
                        DropdownMenuItem(
                            text = { Text(country.country_name) },
                            onClick = {
                                selectedCountry = country
                                viewModel.selectCountry(country)
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.size(32.dp))

            if (selectedCountry != null) {
                ModeCard(title = "Travel", description = "Select this mode for travel information", navController, selectedCountry!!)
                Spacer(Modifier.size(32.dp))
                ModeCard(title = "Interaction", description = "Select this mode for general information", navController, selectedCountry!!)
            }
        }
    }
}

@Composable
fun ModeCard(title: String, description: String, navController: NavController, selectedCountry: Country) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("${Screen.MainCategoryScreen.route}/${selectedCountry.code}")
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.size(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
