package com.example.jetpacknavigationcomponents

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun StringBasedNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        // Home Screen
        composable(Routes.HOME) {
            HomeScreen(
                onNavigateToDetail = { productId ->
                    navController.navigate(Routes.createDetailRoute(productId))
                }
            )
        }

        // Detail Screen
        composable(route = Routes.DETAIL,
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            // Manually extract the argument
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            DetailScreen(
                productId = productId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}