package com.example.jetpacknavigationcomponents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProfileUiState(
    val isLoading: Boolean = false,
    val userProfile: UserProfile? = null,
    val errorMessage: String? = null,
    val isEditing: Boolean = false
)

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun loadProfile(userId: String, userName: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val profile = fetchProfileDetails(userId, userName)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    userProfile = profile
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message
                )
            }
        }
    }

    private suspend fun fetchProfileDetails(userId: String, userName: String): UserProfile {
        kotlinx.coroutines.delay(800)
        return UserProfile(userId, userName, "detailed_avatar.jpg")
    }

    fun toggleEditMode() {
        _uiState.value = _uiState.value.copy(isEditing = !_uiState.value.isEditing)
    }
    
    fun updateProfileName(newName: String) {
        val currentProfile = _uiState.value.userProfile ?: return
        _uiState.value = _uiState.value.copy(
            userProfile = currentProfile.copy(name = newName)
        )
    }
}