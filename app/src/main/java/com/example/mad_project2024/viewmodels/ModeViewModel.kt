package com.example.mad_project2024.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ModeViewModel : ViewModel() {
    private val _mode = MutableStateFlow<String?>(null)
    val mode: StateFlow<String?> = _mode

    fun setMode(mode: String) {
        _mode.value = mode
    }
}
