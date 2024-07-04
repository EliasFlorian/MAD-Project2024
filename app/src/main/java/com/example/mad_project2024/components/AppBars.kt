package com.example.mad_project2024.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mad_project2024.navigation.Screen
import com.example.mad_project2024.viewmodels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val authState = viewModel.authState.collectAsState().value

    TopAppBar(
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
        actions = {
            IconButton(onClick = {
                if (authState.isGuest) {
                    navController.navigate(Screen.AuthScreen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                } else {
                    navController.navigate(route = Screen.AccountScreen.route)
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Account"
                )
            }
        },
    )
}

@Composable
fun BottomBar(navController: NavController) {
    BottomAppBar(
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { navController.navigate(route = Screen.HomeScreen.route) }) {
                    Icon(Icons.Outlined.Home, contentDescription = "Home")
                }
            }
        }
    )
}
