package com.example.mad_project2024.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mad_project2024.models.ContentData
import com.example.mad_project2024.models.Country
import com.example.mad_project2024.models.SubCategory
import com.example.mad_project2024.navigation.Screen
import com.example.mad_project2024.ui.theme.MovieAppMAD24Theme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun CategoryCard(
    navController: NavController,
    title: String,
    description: String,
    subCategories: List<SubCategory>,
    onSubCategoryClick: (SubCategory) -> Unit
) {
    var showInformation by remember { mutableStateOf(true) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    modifier = Modifier.clickable { showInformation = !showInformation },
                    imageVector = if (showInformation) Icons.Filled.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                    contentDescription = "show more"
                )
            }
            AnimatedVisibility(visible = showInformation) {
                Text(
                    text = description,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp, bottom = 16.dp, end = 16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            AnimatedVisibility(visible = !showInformation) {
                Column {
                    subCategories.forEach { subCategory ->
                        SubCategoryCard(navController, subCategory, onSubCategoryClick)
                    }
                }
            }
        }
    }
}

@Composable
fun SubCategoryCard(
    navController: NavController,
    subCategory: SubCategory,
    onSubCategoryClick: (SubCategory) -> Unit
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onSubCategoryClick(subCategory) }
    ) {
        Column {
            Text(
                text = subCategory.title,
                modifier = Modifier.padding(8.dp, bottom = 0.dp),
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = subCategory.description,
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun SubCategoryList(navController: NavController, subCategories: List<SubCategory>, isGuest: Boolean) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(subCategories) { subCategory ->
            SubCategoryContentView(subCategory, isGuest, navController)
        }
    }
}

@Composable
fun SubCategoryContentView(subCategory: SubCategory, isGuest: Boolean, navController: NavController? = null) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement =  Arrangement.Center
    ) {
        items(subCategory.data) { contentData ->
            ContentCard(contentData, isGuest)
            Spacer(modifier = Modifier.height(16.dp))
        }
        if (!isGuest && navController != null) {
            item {
                IconButton(onClick = { navController.navigate("${Screen.SuggestionsScreen.route}/${subCategory.title}") }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            }
        }
    }
}

@Composable
fun ContentCard(contentData: ContentData, isGuest: Boolean) {
    var rating by remember { mutableStateOf(contentData.rating) }
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = contentData.content, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Rating: $rating", style = MaterialTheme.typography.bodyMedium)
            if (!isGuest) {
                Slider(
                    value = rating.toFloat(),
                    onValueChange = { rating = it.toInt() },
                    valueRange = 0f..5f,
                    steps = 4
                )
            }
        }
    }
}


@Composable
fun InteractionModeCard(
    title: String,
    description: String,
    navController: NavController,
    selectedCountry: Country?
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                selectedCountry?.let {
                    navController.navigate("${Screen.InteractionModeScreen.route}/${it.code}")
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelModeCard(
    title: String,
    description: String,
    navController: NavController,
    selectedCountry: Country?
) {
   var pickedStartDate by remember { mutableStateOf(LocalDate.now()) }
   var pickedEndDate by remember { mutableStateOf(LocalDate.now().plusDays(3)) }

   var showStartDatePicker by remember { mutableStateOf(false) }
   var showEndDatePicker by remember { mutableStateOf(false) }
    val dateDialgState = rememberMaterialDialogState()

   val formattedStartDate by remember {
       derivedStateOf {
           DateTimeFormatter.ofPattern("MMM dd yyyy").format(pickedStartDate)
       }
   }
   val formattedEndDate by remember {
       derivedStateOf {
           DateTimeFormatter.ofPattern("MMM dd yyyy").format(pickedEndDate)
       }
   }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .clickable {
                selectedCountry?.let {
                    navController.navigate("${Screen.TravelModeScreen.route}/${it.code}")
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
            Spacer(Modifier.size(16.dp))
            Text(
                text = "Pick your Dates",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { showStartDatePicker = true
                        dateDialgState.show()},
                    modifier = Modifier
                        .width(150.dp)
                        .padding(start = 16.dp, bottom = 10.dp, end = 16.dp)
                ) {
                    Text(text = "Start", fontSize = 16.sp)
                }
                Button(
                    onClick = { showEndDatePicker = true
                              dateDialgState.show()},
                    modifier = Modifier
                        .width(150.dp)
                        .padding(start = 16.dp, bottom = 10.dp, end = 16.dp)
                ) {
                    Text(text = "End", fontSize = 16.sp)
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = formattedStartDate)
                Spacer(modifier = Modifier.padding(16.dp))
                Text(text = formattedEndDate)
            }
        }


          if (showStartDatePicker) {
              DatePickerDialog(
                  onDismissRequest = { showStartDatePicker = false },
                  onDateSelected = { date -> pickedStartDate = date }
              )
          }

          if (showEndDatePicker) {
              DatePickerDialog(
                  onDismissRequest = { showEndDatePicker = false },
                  onDateSelected = { date -> pickedEndDate = date }
              )
          }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    onDismissRequest: () -> Unit,
    onDateSelected: (LocalDate) -> Unit
) {
    val dateDialogState = rememberMaterialDialogState()

    MaterialDialog(
        dialogState = dateDialogState,
        shape = RoundedCornerShape(10.dp),
        buttons = {
            positiveButton(text = "Ok") {

            }
            negativeButton(text = "Cancel") {
                onDismissRequest()
            }
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date"

            )
         { date ->
            onDateSelected(date)
        }
    }
}

