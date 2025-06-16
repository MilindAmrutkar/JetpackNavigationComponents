package com.example.jetpacknavigationcomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.jetpacknavigationcomponents.ui.theme.JetpackNavigationComponentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackNavigationComponentsTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}

