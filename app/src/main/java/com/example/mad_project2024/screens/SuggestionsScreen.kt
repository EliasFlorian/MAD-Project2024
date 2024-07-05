package com.example.mad_project2024.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mad_project2024.models.Suggestion
import com.example.mad_project2024.viewmodels.SuggestionsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestionsScreen(navController: NavController, viewModel: SuggestionsViewModel = hiltViewModel(), subcategory: String) {
    val suggestions by viewModel.suggestions.collectAsState()
    val userRole by viewModel.userRole.collectAsState()
    val homeCountry by viewModel.homeCountry.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Suggestions") }) },
        content = {
            if (userRole == "admin") {
                AdminSuggestionsList(suggestions, viewModel)
            } else {
                UserSuggestionForm(homeCountry, subcategory, viewModel)
            }
        }
    )
}

@Composable
fun AdminSuggestionsList(suggestions: List<Suggestion>, viewModel: SuggestionsViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(suggestions) { suggestion ->
            SuggestionCard(suggestion, viewModel)
        }
    }
}

@Composable
fun SuggestionCard(suggestion: Suggestion, viewModel: SuggestionsViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Content: ${suggestion.suggestedContent.content}")
            Text(text = "Rating: ${suggestion.suggestedContent.rating}")
            Row {
                Button(onClick = { viewModel.approveSuggestion(suggestion) }) {
                    Text("Approve")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { viewModel.declineSuggestion(suggestion) }) {
                    Text("Decline")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { viewModel.editSuggestion(suggestion) }) {
                    Text("Edit")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { viewModel.deleteSuggestion(suggestion) }) {
                    Text("Delete")
                }
            }
        }
    }
}

@Composable
fun UserSuggestionForm(homeCountry: String, subcategory: String, viewModel: SuggestionsViewModel) {
    var content by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Suggest Content for $subcategory")
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Content") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            viewModel.submitSuggestion(homeCountry, subcategory, content.text)
        }) {
            Text("Submit")
        }
    }
}
