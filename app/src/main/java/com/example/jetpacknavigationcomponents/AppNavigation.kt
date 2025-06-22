package com.example.jetpacknavigationcomponents

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreen
    ) {
        composable<HomeScreen> {
            HomeScreenContent(
                onNavigateToProfile = { userId, userName ->
                    navController.navigate(ProfileScreen(
                        userId, userName
                    ))
                },
                onNavigateToSettings = {
                    navController.navigate(SettingsScreen())
                }
            )
        }

        composable<ProfileScreen> { backStackEntry ->
            val profileScreen = backStackEntry.toRoute<ProfileScreen>()
            ProfileScreenContent(
                userId = profileScreen.userId,
                userName = profileScreen.userName,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateDetail = { itemId, category ->
                    navController.navigate(DetailScreen(
                        itemId,
                        category,
                        isEditable = true
                    ))
                }
            )
        }
        
        composable<SettingsScreen> { backStackEntry ->
            val settingsScreen = backStackEntry.toRoute<SettingsScreen>()
            SettingsScreenContent(
                initialTheme = settingsScreen.theme,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable<DetailScreen> { backStackEntry ->
            val detailScreen = backStackEntry.toRoute<DetailScreen>()
            DetailScreenContent(
                itemId = detailScreen.itemId,
                category = detailScreen.category,
                isEditable = detailScreen.isEditable,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}