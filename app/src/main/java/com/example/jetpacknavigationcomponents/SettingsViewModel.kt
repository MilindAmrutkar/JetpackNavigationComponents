package com.example.jetpacknavigationcomponents

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SettingsUiState(
    val theme: String = "light",
    val notificationsEnabled: Boolean = true
)

class SettingsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun setTheme(theme: String) {
        _uiState.value = _uiState.value.copy(theme = theme)
    }

    fun toggleNotifications() {
        _uiState.value = _uiState.value.copy(
            notificationsEnabled = !_uiState.value.notificationsEnabled
        )
    }

    fun initialize(theme: String) {
        _uiState.value = _uiState.value.copy(theme = theme)
    }
} 