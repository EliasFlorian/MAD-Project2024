package com.example.mad_project2024.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mad_project2024.R
import com.example.mad_project2024.ui.theme.MovieAppMAD24Theme
import com.example.mad_project2024.components.TopAppBar
import com.example.mad_project2024.components.BottomBar
import com.example.mad_project2024.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainCategoryScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = stringResource(id = R.string.travel_mode)) },
        bottomBar = { BottomBar() }
    ) { innerPadding ->
        MainView(navController)
    }
}

@Composable
fun MainView(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        item {
            CategoryCard(navController, title = stringResource(R.string.com_general), description = stringResource(R.string.com_description_general))
            Spacer(Modifier.size(56.dp))
        }
        item {
            CategoryCard(navController, title = stringResource(R.string.com_communication), description = stringResource(R.string.com_description_communication))
            Spacer(Modifier.size(56.dp))
        }
        item {
            CategoryCard(navController, title = stringResource(R.string.com_travel), description = stringResource(R.string.com_description_travel))
        }
    }
}

@Composable
fun CategoryCard(navController: NavController, title: String, description: String) {
    var showInformation by remember { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(false) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
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
                    val subCategories = when (title) {
                        stringResource(id = R.string.com_general) -> listOf("Public Holidays", "Healthcare-System", "Restrictions", "Fun facts", "Dos and Don’ts")
                        stringResource(id = R.string.com_communication) -> listOf("Local greetings", "Etiquette", "Expectations", "Popular phrases", "Gestures and facial expressions")
                        stringResource(id = R.string.com_travel) -> listOf("Public Transport", "Payment norms", "Traveling by car", "Traveling on foot", "Risks and dangers", "Shopping", "Gastronomy")
                        else -> emptyList()
                    }
                    subCategories.forEach { subCategory ->
                        SubCategoryCard(
                            navController = navController,
                            category = title,
                            subcategory = subCategory,
                            description = "Description for $subCategory"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SubCategoryCard(navController: NavController, category: String, subcategory: String, description: String) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate("${Screen.SuggestionsScreen.route}/$subcategory") }
    ) {
        Column {
            Text(
                text = subcategory,
                modifier = Modifier.padding(8.dp, bottom = 0.dp),
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = description,
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainCategoryScreenPreview() {
    val navController = rememberNavController()
    MovieAppMAD24Theme {
        MainCategoryScreen(navController = navController)
    }
}
