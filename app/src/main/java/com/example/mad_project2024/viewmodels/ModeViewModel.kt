package com.example.mad_project2024.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_project2024.models.Country
import com.example.mad_project2024.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModeViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    private val _selectedCountry = MutableStateFlow<Country?>(null)
    val selectedCountry: StateFlow<Country?> = _selectedCountry

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            val result = authRepository.getCountries()
            if (result.isSuccess) {
                _countries.value = result.getOrNull() ?: emptyList()
            } else {
                // Handle error
            }
        }
    }

    fun selectCountry(country: Country) {
        _selectedCountry.value = country
    }
}
