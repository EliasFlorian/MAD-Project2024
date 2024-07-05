package com.example.mad_project2024.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_project2024.models.Country
import com.example.mad_project2024.models.TokenManager
import com.example.mad_project2024.repository.AuthRepository
import com.example.mad_project2024.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.security.MessageDigest
import javax.inject.Inject

data class AuthState(
    val isRegister: Boolean = false,
    val username: String = "",
    val displayedName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val homeCountry: String = "",
    val role: String = "GUEST",
    val errorMessage: String? = null,
    val isGuest: Boolean = false // Added isGuest flag
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val userRepository: UserRepository,
    private val tokenManager: TokenManager,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    init {
        fetchUserDetails()
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            val result = repository.getCountries()
            if (result.isSuccess) {
                _countries.value = result.getOrNull() ?: emptyList()
            } else {
                // Handle error
            }
        }
    }

    fun onCountryChange(code: String) {
        _authState.value = _authState.value.copy(homeCountry = code)
    }

    fun onUsernameChange(newUsername: String) {
        _authState.value = _authState.value.copy(username = newUsername)
    }

    fun onDisplayedNameChange(newDisplayedName: String) {
        _authState.value = _authState.value.copy(displayedName = newDisplayedName)
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

    fun toggleAuthMode() {
        _authState.value = _authState.value.copy(
            isRegister = !_authState.value.isRegister,
            errorMessage = null
        )
    }

    private fun hashPassword(password: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val hashedBytes = md.digest(password.toByteArray())
        return hashedBytes.joinToString("") { "%02x".format(it) }
    }

    fun login() {
        viewModelScope.launch {
            val result = repository.login(_authState.value.username, _authState.value.password)
            if (result.isSuccess) {
                val token = result.getOrNull()
                if (token != null) {
                    tokenManager.saveToken(token)
                    Log.d("AuthViewModel", "Token saved: ${token.access_token}")
                    _authState.value = _authState.value.copy(errorMessage = "Logged In successfully!")
                    fetchUserDetails()
                }
            } else {
                _authState.value = _authState.value.copy(errorMessage = result.exceptionOrNull()?.message)
            }
        }
    }

    fun register() {
        viewModelScope.launch {
            if (_authState.value.password != _authState.value.confirmPassword) {
                _authState.value = _authState.value.copy(errorMessage = "Passwords do not match")
            } else if (!repository.isPasswordValid(_authState.value.password)) {
                _authState.value = _authState.value.copy(errorMessage = repository.getPasswordRequirements().joinToString("\n"))
            } else {
                val result = repository.register(
                    _authState.value.username,
                    _authState.value.email,
                    _authState.value.displayedName,
                    _authState.value.password,
                    _authState.value.homeCountry
                )

                if (result.isSuccess) {
                    _authState.value = _authState.value.copy(errorMessage = "Registration successful")
                } else {
                    _authState.value = _authState.value.copy(errorMessage = "Registration failed")
                }
            }
        }
    }

    fun guestLogin() {
        viewModelScope.launch {
            val result = repository.guestAccess()
            if (result.isSuccess) {
                val token = result.getOrNull()
                if (token != null) {
                    tokenManager.saveToken(token)
                    Log.d("AuthViewModel", "Token saved: ${token.access_token}")
                    _authState.value =
                        _authState.value.copy(errorMessage = "Guest access successful!")
                }
            } else {
                _authState.value =
                    _authState.value.copy(errorMessage = result.exceptionOrNull()?.message)
            }
        }
    }

    private fun fetchUserDetails() {
        viewModelScope.launch {
            val token = tokenManager.getToken()?.access_token
            if (token != null) {
                val result = repository.getUserDetails(token)
                if (result.isSuccess) {
                    val userDetails = result.getOrNull()
                    if (userDetails != null) {
                        _authState.value = _authState.value.copy(
                            displayedName = userDetails.displayedName,
                            email = userDetails.eMail,
                            username = userDetails.nickName,
                            homeCountry = userDetails.homeCountry,
                            isGuest = false // Not a guest user
                        )
                    }
                } else {
                    // Handle error
                }
            } else {
                // If no token, it's a guest user
                _authState.value = _authState.value.copy(isGuest = true)
            }
        }
    }

    fun logout() {
        tokenManager.clearToken()
        _authState.value = AuthState() // Reset auth state
    }
}
