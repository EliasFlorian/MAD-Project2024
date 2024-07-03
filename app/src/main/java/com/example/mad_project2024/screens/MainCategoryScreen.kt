package com.example.mad_project2024.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mad_project2024.components.TopAppBar
import com.example.mad_project2024.components.BottomBar
import com.example.mad_project2024.models.InformationResponse
import com.example.mad_project2024.models.Category
import com.example.mad_project2024.models.SubCategory
import com.example.mad_project2024.navigation.Screen
import com.example.mad_project2024.ui.theme.MovieAppMAD24Theme
import com.example.mad_project2024.viewmodels.InformationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainCategoryScreen(
    navController: NavController,
    viewModel: InformationViewModel = hiltViewModel(),
    countryCode: String
) {
    val information by viewModel.information.collectAsState()

    LaunchedEffect(countryCode) {
        viewModel.fetchInformation(countryCode)
    }

    Scaffold(

        topBar = { TopAppBar(title = stringResource(id = R.string.travel_mode), navController) },
        bottomBar = { BottomBar(navController) }

   //     topBar = { TopAppBar(title = "Categories") },
   //     bottomBar = { BottomBar() }

    ) { innerPadding ->
        MainView(navController, information)
    }
}

@Composable
fun MainView(navController: NavController, information: InformationResponse?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        information?.categories?.let { categories ->
            items(categories) { category ->
                CategoryCard(navController, category)
                Spacer(Modifier.size(56.dp))
            }
        }
    }
}

@Composable
fun CategoryCard(navController: NavController, category: Category) {
    var showInformation by remember { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(false) }

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
                    text = category.title,
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
                    text = category.description,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp, bottom = 16.dp, end = 16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            AnimatedVisibility(visible = !showInformation) {
                Column {
                    category.subCategories.forEach { subCategory ->
                        SubCategoryCard(navController, category.title, subCategory)
                    }
                }
            }
        }
    }
}

@Composable
fun SubCategoryCard(navController: NavController, categoryTitle: String, subCategory: SubCategory) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate("${Screen.SuggestionsScreen.route}/${subCategory.title}") }
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

@Preview(showBackground = true)
@Composable
fun MainCategoryScreenPreview() {
    val navController = rememberNavController()
    MovieAppMAD24Theme {
        MainCategoryScreen(navController = navController, countryCode = "US")
    }
}
