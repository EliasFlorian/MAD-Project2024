package com.example.mad_project2024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mad_project2024.navigation.Navigation
import com.example.mad_project2024.navigation.Screen
import com.example.mad_project2024.screens.AuthScreen
import com.example.mad_project2024.screens.MainCategoryScreen
import com.example.mad_project2024.screens.StartScreen
import com.example.mad_project2024.ui.theme.MovieAppMAD24Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                Navigation()
            }
        }
    }
}
