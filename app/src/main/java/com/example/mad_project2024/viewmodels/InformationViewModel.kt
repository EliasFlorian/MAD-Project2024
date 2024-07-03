package com.example.mad_project2024.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_project2024.models.InformationResponse
import com.example.mad_project2024.repository.InformationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InformationViewModel @Inject constructor(
    private val informationRepository: InformationRepository
) : ViewModel() {

    private val _information = MutableStateFlow<InformationResponse?>(null)
    val information: StateFlow<InformationResponse?> = _information

    fun fetchInformation(countryCode: String) {
        viewModelScope.launch {
            val result = informationRepository.getInformation(countryCode)
            if (result.isSuccess) {
                _information.value = result.getOrNull()
            } else {
                // Handle error
                _information.value = null
            }
        }
    }
}
