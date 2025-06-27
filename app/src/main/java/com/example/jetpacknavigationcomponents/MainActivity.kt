package com.example.jetpacknavigationcomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.jetpacknavigationcomponents.ui.theme.JetpackNavigationComponentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackNavigationComponentsTheme {
                val vm: StudentViewModel = viewModel()
                val backStack = rememberNavBackStack<NavKey>(StudentListKey)

                NavDisplay(
                    backStack = backStack,
                    entryProvider = entryProvider<NavKey> {
                        entry<StudentListKey> {
                            StudentListScreen(
                                students = vm.students,
                                onStudentClick = { id ->
                                    backStack.add(StudentDetailKey(id))
                                }
                            )
                        }

                        entry<StudentDetailKey> { detail ->
                            val student = vm.getStudent(detail.studentId)
                            StudentDetailScreen(
                                student = student,
                                onPhoneChange = { newPhone ->
                                    vm.updatePhone(detail.studentId, newPhone)
                                },
                                onBack = {
                                    backStack.remove(detail)
                                }
                            )
                        }
                    }
                )
            }
        }
    }
}

