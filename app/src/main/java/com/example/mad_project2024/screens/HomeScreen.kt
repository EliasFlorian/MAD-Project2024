package com.example.mad_project2024.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mad_project2024.R
import com.example.mad_project2024.components.BottomBar
import com.example.mad_project2024.components.TopAppBar
import com.example.mad_project2024.models.Country
import com.example.mad_project2024.navigation.Screen
import com.example.mad_project2024.ui.theme.MovieAppMAD24Theme
import com.example.mad_project2024.viewmodels.AuthViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, viewModel: AuthViewModel = hiltViewModel()) {
    val authState by viewModel.authState.collectAsState()
    val countries by viewModel.countries.collectAsState(emptyList())
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf<Country?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = stringResource(id = R.string.app_name), navController)
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
            TravelModeCard(title = stringResource(R.string.travel_mode), description = stringResource(R.string.description_travel_mode), navController, selectedCountry)
            Spacer(Modifier.size(64.dp))
            ModeCard(title = stringResource(R.string.general_mode), description = stringResource(R.string.description_general_mode), navController, selectedCountry)
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

@Composable
fun ModeCard(title: String, description: String, navController: NavController, selectedCountry: Country?) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (selectedCountry != null) {
                    if (title == "Travel Mode") {
                        navController.navigate("${Screen.MainCategoryScreen.route}/travel/${selectedCountry.code}")
                    } else if (title == "Interaction Mode") {
                        navController.navigate("${Screen.MainCategoryScreen.route}/interaction/${selectedCountry.code}")
                    }
                }
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

@Composable
fun TravelModeCard(title: String, description: String, navController: NavController, selectedCountry: Country?) {
    val dateDialogState = rememberMaterialDialogState()

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (selectedCountry != null) {
                    navController.navigate("${Screen.MainCategoryScreen.route}/travel/${selectedCountry.code}")
                }
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
            Spacer(Modifier.size(8.dp))
            Text(
                text = "Pick your Dates",
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { dateDialogState.show() },
                    modifier = Modifier
                        .width(150.dp)
                        .padding(start = 16.dp, bottom = 10.dp, end = 16.dp)
                ) {
                    Text(text = "Start", fontSize = 16.sp)
                }
                Button(
                    onClick = { dateDialogState.show() },
                    modifier = Modifier
                        .width(150.dp)
                        .padding(start = 16.dp, bottom = 10.dp, end = 16.dp)
                ) {
                    Text(text = "End", fontSize = 16.sp)
                }
            }

            MaterialDialog(
                dialogState = dateDialogState,
                shape = RoundedCornerShape(10.dp),
                buttons = {
                    positiveButton(text = "Ok")
                    negativeButton(text = "Cancel")
                }
            ) {
                datepicker(
                    initialDate = LocalDate.now(),
                    title = "pick a date",
                    colors = DatePickerDefaults.colors(
                        headerBackgroundColor = MaterialTheme.colorScheme.primary,
                        dateActiveBackgroundColor = MaterialTheme.colorScheme.primary,
                    )
                )
            }
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MovieAppMAD24Theme {
        HomeScreen(rememberNavController(), viewModel = hiltViewModel())
    }
}
*/