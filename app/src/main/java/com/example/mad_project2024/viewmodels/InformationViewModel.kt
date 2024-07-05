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
    val isGuest: Boolean = true // This should be set based on the actual user status
)

@HiltViewModel
class InformationViewModel @Inject constructor(
    private val repository: InformationRepository
) : ViewModel() {

    private val _informationState = MutableStateFlow(InformationState())
    val informationState: StateFlow<InformationState> = _informationState

    fun fetchInformation(countryCode: String) {
        viewModelScope.launch {
            val result = repository.getInformation(countryCode)
            if (result.isSuccess) {
                _informationState.value = InformationState(information = result.getOrNull())
            } else {
                // Handle error state
            }
        }
    }
}
