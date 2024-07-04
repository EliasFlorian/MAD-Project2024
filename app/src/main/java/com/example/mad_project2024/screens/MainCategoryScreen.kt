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
import com.example.mad_project2024.viewmodels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainCategoryScreen(
    navController: NavController,
    viewModel: InformationViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
    countryCode: String,
    mode: String
) {
    val informationState by viewModel.informationState.collectAsState()
    val authState by authViewModel.authState.collectAsState()
    var selectedSubCategory by remember { mutableStateOf<SubCategory?>(null) }

    LaunchedEffect(countryCode) {
        Log.d("MainCategoryScreen", "Fetching information for country: $countryCode")
        viewModel.fetchInformation(countryCode)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = selectedSubCategory?.title ?: "Categories") },
                actions = {
                    if (selectedSubCategory != null && !authState.isGuest) {
                        IconButton(onClick = { navController.navigate("${Screen.SuggestionsScreen.route}/${selectedSubCategory!!.title}") }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                }
            )
        },
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        informationState.information?.let { information ->
            if (selectedSubCategory == null) {
                if (mode == "interaction") {
                    // Directly show "Communication" category and its subcategories
                    val communicationCategory = information.categories.find { it.title == "Communication" }
                    communicationCategory?.let {
                        SubCategoryList(it.subCategories, authState.role, navController)
                    }
                } else {
                    // Show all categories for travel mode
                    MainView(navController, information, onSubCategoryClick = { subCategory ->
                        selectedSubCategory = subCategory
                    })
                }
            } else {
                SubCategoryContentView(subCategory = selectedSubCategory!!, role = authState.role, navController)
            }
        }
    }
}

@Composable
fun MainView(
    navController: NavController,
    informationResponse: InformationResponse,
    onSubCategoryClick: (SubCategory) -> Unit
) {
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
fun SubCategoryList(subCategories: List<SubCategory>, role: String, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(subCategories) { subCategory ->
            SubCategoryContentView(subCategory, role, navController)
        }
    }
}

@Composable
fun SubCategoryContentView(subCategory: SubCategory, role: String, navController: NavController? = null) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(subCategory.data) { contentData ->
            ContentCard(contentData, role)
            Spacer(modifier = Modifier.height(16.dp))
        }
        if (role != "GUEST" && navController != null) {
            item {
                IconButton(onClick = { navController.navigate("${Screen.SuggestionsScreen.route}/${subCategory.title}") }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            }
        }
    }
}

@Composable
fun ContentCard(contentData: ContentData, role: String) {
    var rating by remember { mutableStateOf(contentData.rating) }
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = contentData.content, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Rating: $rating", style = MaterialTheme.typography.bodyMedium)
            if (role != "GUEST") {
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
