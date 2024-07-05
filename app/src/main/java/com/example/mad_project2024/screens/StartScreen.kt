package com.example.mad_project2024.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mad_project2024.R
import com.example.mad_project2024.navigation.Screen
import com.example.mad_project2024.ui.theme.MovieAppMAD24Theme




@Composable
fun StartScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Image(
            painter = painterResource(id = R.drawable.clay_banks_b5s4frjb7yq_unsplash), // replace with your image resource
            contentDescription = "Start Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Light,
                    fontSize = 96.sp
                )
                Text(
                    text = "Navigate Cultures,\nFoster Connections",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Light,
                    fontSize = 32.sp,
                    lineHeight = 36.sp,
                    textAlign = TextAlign.Left
                )
            }

            Column {
                Button(
                    onClick = { navController.navigate(Screen.AuthScreen.route) },

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                ) {
                    Text(text = "Login / Register", fontSize = 16.sp)
                }
                Button(
                    onClick = { navController.navigate(Screen.HomeScreen.route) },

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp)
                ) {
                    Text(text = "Continue as a Guest", fontSize = 16.sp)
                }
            }


        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewStartScreen() {
    MovieAppMAD24Theme {
        StartScreen(rememberNavController())
    }
}