package com.example.jetpacknavigationcomponents

object Routes {
    const val HOME = "home"
    const val DETAIL = "detail/{productId}"

    fun createDetailRoute(productId: String) = "detail/$productId"
}