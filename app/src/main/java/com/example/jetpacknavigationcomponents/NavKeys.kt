package com.example.jetpacknavigationcomponents

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppKey: NavKey

@Serializable
object StudentListKey : AppKey

@Serializable
data class StudentDetailKey(val studentId: Int) : AppKey