package com.example.jetpacknavigationcomponents

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Detail(val productId: String)