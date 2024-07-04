package com.example.mad_project2024.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.example.mad_project2024.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(title: String, navController: NavController) {

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),

        title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate(route = Screen.AccountScreen.route) }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Localized description"
                )
            }
        },

        )

}


@Composable
fun BottomBar(
navController: NavController
) {
    BottomAppBar(
        actions = {
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center)
            {
                IconButton(onClick = { navController.navigate(route = Screen.HomeScreen.route) }) {
                    Icon(Icons.Outlined.Home, contentDescription = "Localized description")
                }
//                IconButton(onClick = { /* do something */ }) {
//                    Icon(
//                        Icons.Filled.Favorite,
//                        contentDescription = "Localized description",
//                    )
//                }
//                IconButton(onClick = { /* do something */ }) {
//                    Icon(
//                        Icons.Filled.AccountCircle,
//                        contentDescription = "Localized description",
//                    )
//                }
            }


        }
    )
}