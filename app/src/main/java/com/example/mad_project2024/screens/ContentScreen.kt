package com.example.mad_project2024.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mad_project2024.components.BottomBar
import com.example.mad_project2024.components.TopAppBar
import com.example.mad_project2024.components.ContentCard
import com.example.mad_project2024.models.ContentData
import com.example.mad_project2024.models.SubCategory
import com.example.mad_project2024.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContentScreen(
    navController: NavController,
    subCategory: SubCategory,
    isGuest: Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = subCategory.title) },
                actions = {
                    if (!isGuest) {
                        IconButton(onClick = { navController.navigate("${Screen.SuggestionsScreen.route}/${subCategory!!.title}") }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                }
            )
        },
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(subCategory.data) { contentData ->
                ContentCard(contentData, isGuest)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
