package com.example.jetpacknavigationcomponents

sealed class Screen(val route: String) {
    data object Home : Screen ("home")
    data object Detail : Screen ("detail/{itemId}") {
        fun createRoute(itemId: String) = "detail/$itemId"
    }
}