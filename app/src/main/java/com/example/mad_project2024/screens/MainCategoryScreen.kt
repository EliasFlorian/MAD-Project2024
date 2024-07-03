package com.example.mad_project2024.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mad_project2024.components.TopAppBar
import com.example.mad_project2024.components.BottomBar
import com.example.mad_project2024.models.InformationResponse
import com.example.mad_project2024.models.SubCategory
import com.example.mad_project2024.models.ContentData
import com.example.mad_project2024.navigation.Screen
import com.example.mad_project2024.viewmodels.InformationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainCategoryScreen(
    navController: NavController,
    viewModel: InformationViewModel = hiltViewModel(),
    countryCode: String
) {
    val mainCategories by viewModel.information.collectAsState()
    var selectedSubCategory by remember { mutableStateOf<SubCategory?>(null) }

    LaunchedEffect(countryCode) {
        Log.d("MainCategoryScreen", "Fetching information for country: $countryCode")
        viewModel.fetchInformation(countryCode)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = selectedSubCategory?.title ?: "Categories"
                    )
                },
                actions = {
                    if (selectedSubCategory != null) {
                        IconButton(onClick = { /* navigate to add suggestion screen */ }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                }
            )
        },
        bottomBar = { BottomBar() }
    ) { innerPadding ->
        mainCategories?.let {
            if (selectedSubCategory == null) {
                MainView(navController, it, countryCode, onSubCategoryClick = { subCategory ->
                    selectedSubCategory = subCategory
                })
            } else {
                SubCategoryContentView(subCategory = selectedSubCategory!!)
            }
        }
    }
}

@Composable
fun MainView(
    navController: NavController,
    informationResponse: InformationResponse,
    countryCode: String,
    onSubCategoryClick: (SubCategory) -> Unit
) {
    LaunchedEffect(Unit) {
        Log.d("MainView", "Main categories updated: ${informationResponse.categories}")
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        items(informationResponse.categories) { category ->
            if (category.title != "Public Holidays") { // Comment out Public Holidays
                CategoryCard(navController, category.title, category.description, category.subCategories, onSubCategoryClick)
                Spacer(Modifier.size(56.dp))
            }
        }
    }
}

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
fun SubCategoryContentView(subCategory: SubCategory) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(subCategory.data) { contentData ->
            ContentCard(contentData)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ContentCard(contentData: ContentData) {
    var rating by remember { mutableStateOf(contentData.rating) }
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = contentData.content, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Rating: $rating", style = MaterialTheme.typography.bodyMedium)
            Slider(
                value = rating.toFloat(),
                onValueChange = { rating = it.toInt() },
                valueRange = 0f..5f,
                steps = 4
            )
        }
    }
}
