package com.example.jetpacknavigationcomponents

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun TypeSafeNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home // Using the object directly, not a string
    ) {
        // Home screen
        composable<Home> {
            HomeScreen(
                onNavigateToDetail = {productId ->
                    navController.navigate(Detail(productId))
                }
            )
        }

        // Detail screen
        composable<Detail> { backStackEntry ->
            // Extract type-safe arguments
            val detail =  backStackEntry.toRoute<Detail>()
            DetailScreen(
                productId = detail.productId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}