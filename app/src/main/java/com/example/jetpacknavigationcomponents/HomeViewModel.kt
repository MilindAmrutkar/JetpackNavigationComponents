package com.example.jetpacknavigationcomponents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val isLoading: Boolean = false,
    val userProfiles: List<UserProfile> = emptyList(),
    val errorMessage: String? = null
)

data class UserProfile(
    val id: String,
    val name: String,
    val avatar: String
)

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadUserProfiles()
    }

    private fun loadUserProfiles() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                // Simulate API call
                val profiles = fetchUserProfiles()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    userProfiles = profiles
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message
                )
            }
        }
    }

    private suspend fun fetchUserProfiles(): List<UserProfile> {
        // Simulate network delay
        kotlinx.coroutines.delay(1000)
        return listOf(
            UserProfile("1", "Milind Amrutkar", "avatar1.jpg"),
            UserProfile("2", "Ramesh", "avatar2.jpg"),
            UserProfile("3", "Himesh", "avatar3.jpg"),
        )
    }

    fun refreshProfiles() {
        loadUserProfiles()
    }

}















