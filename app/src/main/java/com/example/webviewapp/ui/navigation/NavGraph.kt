package com.example.webviewapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.webviewapp.ui.game_screen.GameScreen
import com.example.webviewapp.ui.main_screen.MainScreen
import com.example.webviewapp.ui.no_internet_screen.NoInternetScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.MainScreen.route) {
            MainScreen()
        }
        composable(Screen.GameScreen.route) {
            GameScreen()
        }
        composable(Screen.NoInternetScreen.route) {
            NoInternetScreen()
        }
    }

}