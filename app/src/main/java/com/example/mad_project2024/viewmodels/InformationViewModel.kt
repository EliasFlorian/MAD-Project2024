package com.example.mad_project2024.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_project2024.models.InformationResponse
import com.example.mad_project2024.repository.InformationRepository
import com.example.mad_project2024.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class InformationState(
    val information: InformationResponse? = null,
    val errorMessage: String? = null,
    val role: String? = null
)

@HiltViewModel
class InformationViewModel @Inject constructor(
    private val informationRepository: InformationRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _informationState = MutableStateFlow(InformationState())
    val informationState: StateFlow<InformationState> = _informationState

    fun fetchInformation(countryCode: String) {
        viewModelScope.launch {
            val userResult = userRepository.getSelfUser()
            if (userResult.isSuccess) {
                val user = userResult.getOrNull()
                val role = user?.role ?: "GUEST"
                val result = informationRepository.getInformation(countryCode)
                if (result.isSuccess) {
                    _informationState.value = InformationState(information = result.getOrNull(), role = role)
                } else {
                    _informationState.value = InformationState(errorMessage = result.exceptionOrNull()?.message, role = role)
                }
            } else {
                _informationState.value = InformationState(errorMessage = userResult.exceptionOrNull()?.message, role = "GUEST")
            }
        }
    }
}
