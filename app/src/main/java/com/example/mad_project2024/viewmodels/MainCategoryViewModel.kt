package com.example.mad_project2024.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_project2024.models.SubCategory
import com.example.mad_project2024.repository.SuggestionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainCategoryViewModel @Inject constructor(
    private val repository: SuggestionsRepository
) : ViewModel() {
    private val _subCategories = MutableStateFlow<List<SubCategory>>(emptyList())
    val subCategories: StateFlow<List<SubCategory>> = _subCategories

    fun fetchSubCategories(countryCode: String) {
        viewModelScope.launch {
            val result = repository.getSubCategories(countryCode)
            if (result.isSuccess) {
                _subCategories.value = result.getOrNull() ?: emptyList()
            } else {
                // Handle error
            }
        }
    }
}
