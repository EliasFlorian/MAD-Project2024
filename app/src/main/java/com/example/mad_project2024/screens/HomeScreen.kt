package com.example.mad_project2024.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.key.Key.Companion.Calendar
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.setValue
import androidx.fragment.app.DialogFragment;

import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mad_project2024.R
import com.example.mad_project2024.ui.theme.MovieAppMAD24Theme
import com.example.mad_project2024.components.TopAppBar
import com.example.mad_project2024.components.BottomBar
import com.example.mad_project2024.models.Country
import com.example.mad_project2024.navigation.Screen
import com.google.android.material.datepicker.MaterialDatePicker
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale
import kotlin.math.round


import com.example.mad_project2024.viewmodels.AuthViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, viewModel: AuthViewModel = hiltViewModel()) {
    val authState by viewModel.authState.collectAsState()
    val countries by viewModel.countries.collectAsState(emptyList())
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf<Country?>(null) }

    Scaffold (

        topBar = {
            TopAppBar(title = stringResource(id = R.string.app_name), navController)
        },
        bottomBar = {
            BottomBar(navController)
        }

    ) {innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            TravelModeCard(title = stringResource(R.string.travel_mode), description = stringResource(R.string.description_travel_mode), navController)
            Spacer(Modifier.size(64.dp))
            ModeCard(title = stringResource(R.string.general_mode), description = stringResource(R.string.description_general_mode), navController)
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
fun ModeCard(title: String, description: String, navController: NavController) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (title == "Travel Mode") {
                    navController.navigate(route = Screen.MainCategoryScreen.route)
                }
                if (title == "Interaction Mode") {
                    navController.navigate(route = Screen.MainCategoryScreen.route)
                }
            }
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.size(8.dp))

        Text(
            text = description,
            modifier = Modifier.padding(start = 16.dp, top = 0.dp, bottom = 16.dp, end = 16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

@Composable
fun TravelModeCard(title: String, description: String, navController: NavController) {

    val getDatePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Select date")
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        .setPositiveButtonText("Submit")
        .build()

    val dateDialogState = rememberMaterialDialogState()

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (title == "Travel Mode") {
                    navController.navigate(route = Screen.MainCategoryScreen.route)
                }
                if (title == "Interaction Mode") {
                    navController.navigate(route = Screen.MainCategoryScreen.route)
                }
            }
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.size(8.dp))

        Text(
            text = description,
            modifier = Modifier.padding(start = 16.dp, top = 0.dp, bottom = 16.dp, end = 16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Pick your Dates",
            Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Center

        )
        Row(modifier = Modifier
            .fillMaxWidth(),
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

            //datepicker
            MaterialDialog(
                dialogState = dateDialogState,
                shape = RoundedCornerShape(10.dp),
                buttons = {
                    positiveButton(text = "Ok")
                    negativeButton(
                        text = "Cancel")

                }
                //backgroundColor = MaterialTheme.colorScheme.primary
            ) {
                datepicker(
                    initialDate = LocalDate.now(),
                    title = "pick a date",
                    colors = DatePickerDefaults.colors(
                        headerBackgroundColor = MaterialTheme.colorScheme.primary,
                        //dateInactiveTextColor = MaterialTheme.colorScheme.primary,
                        dateActiveBackgroundColor = MaterialTheme.colorScheme.primary,

                    )

                )
            }
        }



    }

}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MovieAppMAD24Theme {
        HomeScreen(rememberNavController(), viewModel = hiltViewModel())
    }
}

