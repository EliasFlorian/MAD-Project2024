package com.example.mad_project2024.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_project2024.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthState(
    val username: String = "",
    val displayedName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isRegister: Boolean = false,
    val errorMessage: String? = null
)

class AuthViewModel : ViewModel() {
    private val _authState = MutableStateFlow(AuthState())
    private val repository = AuthRepository();
    val authState = _authState.asStateFlow()

    fun onUsernameChange(newUsername: String) {
        _authState.value = _authState.value.copy(username = newUsername)
    }

    fun onEmailChange(newEmail: String) {
        _authState.value = _authState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _authState.value = _authState.value.copy(password = newPassword)
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        _authState.value = _authState.value.copy(confirmPassword = newConfirmPassword)
    }

    fun onDisplayedNameChange(newDisplayedName: String) {
        _authState.value = _authState.value.copy(displayedName = newDisplayedName)
    }

    fun toggleAuthMode() {
        _authState.value = _authState.value.copy(isRegister = !_authState.value.isRegister)
    }

    fun login() {
        // Simulate API call
        viewModelScope.launch {
            //TODO Has Password before repository.login
            val result = repository.login(_authState.value.username, _authState.value.password)
            if (result.isSuccess) {
                _authState.value = _authState.value.copy(errorMessage = "Logged In successfully!")
            } else {
                _authState.value = _authState.value.copy(errorMessage = result.exceptionOrNull()?.message)
            }
        }
    }

    fun register() {
        // Simulate API call
        viewModelScope.launch {
            if (_authState.value.password != _authState.value.confirmPassword) {
                _authState.value = _authState.value.copy(errorMessage = "Passwords do not match")
            } else {
                //TODO Has Password before repository.register
                val result = repository.register(_authState.value.username,
                    _authState.value.email, _authState.value.displayedName, _authState.value.password)

                if (result.isSuccess){
                    _authState.value = _authState.value.copy(errorMessage = "Registration successful")
                } else {
                    _authState.value = _authState.value.copy(errorMessage = "Registration failed")
                }
            }
        }
    }
}