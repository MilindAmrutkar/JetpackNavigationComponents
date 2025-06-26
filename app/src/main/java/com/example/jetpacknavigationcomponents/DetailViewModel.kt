package com.example.jetpacknavigationcomponents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DetailUiState(
    val isLoading: Boolean = false,
    val itemDetails: ItemDetail? = null,
    val errorMessage: String? = null
)

data class ItemDetail(
    val id: Int,
    val title: String,
    val description: String,
    val category: String
)

class DetailViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun loadItemDetails(itemId: Int, category: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val itemDetail = fetchItemDetails(itemId, category)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    itemDetails = itemDetail
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message
                )
            }
        }
    }

    private suspend fun fetchItemDetails(itemId: Int, category: String): ItemDetail {
        // Simulate network delay
        kotlinx.coroutines.delay(500)
        return ItemDetail(
            id = itemId,
            title = "Item $itemId",
            description = "This is a detailed description for item $itemId in category $category.",
            category = category
        )
    }
} 