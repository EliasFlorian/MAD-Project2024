package com.example.mad_project2024.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_project2024.models.user.ListUser
import com.example.mad_project2024.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserState(
    val user: ListUser? = null,
    val errorMessage: String? = null
)

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userState = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState

    init {
        fetchSelfUser()
    }

    private fun fetchSelfUser() {
        viewModelScope.launch {
            val result = userRepository.getSelfUser()
            if (result.isSuccess) {
                _userState.value = UserState(user = result.getOrNull())
            } else {
                _userState.value = UserState(errorMessage = result.exceptionOrNull()?.message)
            }
        }
    }
}
