package com.example.mad_project2024.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_project2024.models.Suggestion
import com.example.mad_project2024.repository.SuggestionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestionsViewModel @Inject constructor(
    private val suggestionsRepository: SuggestionsRepository
) : ViewModel() {

    private val _suggestions = MutableStateFlow<List<Suggestion>>(emptyList())
    val suggestions: StateFlow<List<Suggestion>> = _suggestions

    private val _userRole = MutableStateFlow("regular")
    val userRole: StateFlow<String> = _userRole

    private val _homeCountry = MutableStateFlow("AFG")  // Replace with actual default
    val homeCountry: StateFlow<String> = _homeCountry

    fun fetchSuggestions() {
        viewModelScope.launch {
            val result = suggestionsRepository.getSuggestions()
            if (result.isSuccess) {
                _suggestions.value = result.getOrNull() ?: emptyList()
            }
        }
    }

    fun submitSuggestion(homeCountry: String, subcategory: String, content: String) {
        viewModelScope.launch {
            suggestionsRepository.submitSuggestion(homeCountry, subcategory, content)
        }
    }

    fun approveSuggestion(suggestion: Suggestion) {
        viewModelScope.launch {
            suggestionsRepository.approveSuggestion(suggestion.id)
        }
    }

    fun declineSuggestion(suggestion: Suggestion) {
        viewModelScope.launch {
            suggestionsRepository.declineSuggestion(suggestion.id)
        }
    }

    fun editSuggestion(suggestion: Suggestion) {
        // Implement editing logic
    }

    fun deleteSuggestion(suggestion: Suggestion) {
        viewModelScope.launch {
            suggestionsRepository.deleteSuggestion(suggestion.id)
        }
    }
}
