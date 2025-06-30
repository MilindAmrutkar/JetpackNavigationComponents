package com.example.jetpacknavigationcomponents

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {

    private val _students = mutableStateListOf(
        Student(
            id = 1,
            name = "Ram Shyam",
            phoneNumber = "900000000",
            country = "India",
            degree = "B.Tech",
        ),
        Student(
            id = 2,
            name = "Shiv Raj",
            phoneNumber = "900000001",
            country = "India",
            degree = "MSc Physics",
        ),
        Student(
            id = 3,
            name = "Ganesh Shankar",
            phoneNumber = "900000002",
            country = "India",
            degree = "BA Economics",
        ),
    )

    val students: List<Student> = _students

    fun getStudent(id: Int) = _students.first { it.id == id }

    fun updatePhone(id: Int, newPhone: String) {
        val idx = _students.indexOfFirst { it.id == id }
        if (idx >= 0) {
            _students[idx] = _students[idx].copy(phoneNumber = newPhone)
        }
    }
}

data class Student(
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val country: String,
    val degree: String,
)