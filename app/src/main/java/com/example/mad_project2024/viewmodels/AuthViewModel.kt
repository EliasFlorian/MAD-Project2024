package com.example.mad_project2024.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isRegister: Boolean = false,
    val errorMessage: String? = null
)

class AuthViewModel : ViewModel() {
    private val _authState = MutableStateFlow(AuthState())
    val authState = _authState.asStateFlow()

    fun onEmailChange(newEmail: String) {
        _authState.value = _authState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _authState.value = _authState.value.copy(password = newPassword)
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        _authState.value = _authState.value.copy(confirmPassword = newConfirmPassword)
    }

    fun toggleAuthMode() {
        _authState.value = _authState.value.copy(isRegister = !_authState.value.isRegister)
    }

    fun login() {
        // Simulate API call
        viewModelScope.launch {
            _authState.value = _authState.value.copy(errorMessage = "Login successful")
        }
    }

    fun register() {
        // Simulate API call
        viewModelScope.launch {
            if (_authState.value.password != _authState.value.confirmPassword) {
                _authState.value = _authState.value.copy(errorMessage = "Passwords do not match")
            } else {
                _authState.value = _authState.value.copy(errorMessage = "Registration successful")
            }
        }
    }
}
