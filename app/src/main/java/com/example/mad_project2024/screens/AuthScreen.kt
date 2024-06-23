package com.example.mad_project2024.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mad_project2024.models.Country
import com.example.mad_project2024.viewmodels.AuthViewModel

@Composable
fun AuthScreen(viewModel: AuthViewModel = viewModel()) {
    val authState by viewModel.authState.collectAsState()
    val countries by viewModel.countries.collectAsState(emptyList())
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf<Country?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (authState.isRegister) "Register" else "Login",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = authState.username,
            onValueChange = { viewModel.onUsernameChange(it) },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (!authState.isRegister) {
            OutlinedTextField(
                value = authState.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
        } else {
            OutlinedTextField(
                value = authState.displayedName,
                onValueChange = { viewModel.onDisplayedNameChange(it) },
                label = { Text("Displayed Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = authState.email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = authState.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            OutlinedTextField(
                value = authState.confirmPassword,
                onValueChange = { viewModel.onConfirmPasswordChange(it) },
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = selectedCountry?.country_name ?: "Select Country",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isDropdownExpanded = true },
                    label = { Text("Country") },
                    readOnly = true
                )
                DropdownMenu(
                    expanded = isDropdownExpanded,
                    onDismissRequest = { isDropdownExpanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    countries.forEach { country ->
                        DropdownMenuItem(
                            text = { Text(text = country.country_name) },
                            onClick = {
                                selectedCountry = country
                                viewModel.onCountryChange(country.code)
                                isDropdownExpanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (authState.isRegister) {
                    viewModel.register()
                } else {
                    viewModel.login()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (authState.isRegister) "Register" else "Login")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { viewModel.toggleAuthMode() }) {
            Text(text = if (authState.isRegister) "Already have an account? Login" else "Don't have an account? Register")
        }

        authState.errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
    }
}
