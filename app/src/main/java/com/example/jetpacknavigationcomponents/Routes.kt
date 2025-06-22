package com.example.jetpacknavigationcomponents

import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
data class ProfileScreen(val userId: String, val userName: String)

@Serializable
data class SettingsScreen(val theme: String = "light")

@Serializable
data class DetailScreen(
    val itemId: Int,
    val category: String,
    val isEditable: Boolean = false
)