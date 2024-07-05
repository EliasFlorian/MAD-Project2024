package com.example.mad_project2024.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mad_project2024.R
import com.example.mad_project2024.components.BottomBar
import com.example.mad_project2024.components.TopAppBar
import com.example.mad_project2024.ui.theme.MovieAppMAD24Theme
import com.example.mad_project2024.viewmodels.AuthViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mad_project2024.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountScreen(navController: NavController, viewModel: AuthViewModel = hiltViewModel()) {
    val authState by viewModel.authState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = "Account", navController)
        },
        bottomBar = {
            BottomBar(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center
        ) {
            ProfileCard(
                displayName = authState.displayedName,
                email = authState.email,
                nickname = authState.username, // Assuming the role is stored in username
                onLogout = {
                    viewModel.logout()
                    navController.navigate(Screen.AuthScreen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun ProfileCard(displayName: String, email: String, nickname: String, onLogout: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomImage(modifier = Modifier.size(150.dp))

            Divider()

            ProfileInfo(displayName = displayName, email = email, nickname = nickname)

            Button(
                onClick = onLogout,
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Log Out", style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}

@Composable
fun ProfileInfo(displayName: String, email: String, nickname: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = displayName, style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
        Text(text = email, modifier = Modifier.padding(3.dp))
        Text(text = nickname, modifier = Modifier.padding(3.dp), style = MaterialTheme.typography.titleSmall)
    }
}

@Composable
fun CustomImage(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.padding(16.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shadowElevation = 4.dp
    ) {
        Image(
            painter = painterResource(id = R.drawable.gael_gaborel_orbisterrae_f7i8ueiykag_unsplash),
            contentDescription = "profile image",
            modifier = modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
    }
}
/*
@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    MovieAppMAD24Theme {
        AccountScreen(rememberNavController())
    }
}
*/