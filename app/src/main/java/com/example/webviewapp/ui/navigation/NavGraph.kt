package com.example.webviewapp.ui.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.webviewapp.ui.game_screen.GameScreen
import com.example.webviewapp.ui.main_screen.MainScreen
import com.example.webviewapp.ui.no_internet_screen.NoInternetScreen
import com.example.webviewapp.ui.utils.Constants
import com.example.webviewapp.ui.wev_view_screen.WebViewScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.MainScreen.route) {
            MainScreen(
                navigateToNoInternet = { navController.navigate(Screen.NoInternetScreen.route) },
                navigateToGame = { navController.navigate(Screen.GameScreen.route) },
                navigateToWebView = { url ->
                    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                    navController.navigate(Screen.WebViewScreen.route + "/$encodedUrl")
                })
        }
        composable(Screen.GameScreen.route) {
            GameScreen()
        }
        composable(Screen.NoInternetScreen.route) {
            NoInternetScreen()
        }
        composable(Screen.WebViewScreen.route + "/{${Constants.URL}}") {
            WebViewScreen(it.arguments?.getString(Constants.URL) ?: "")
        }
    }

}

fun NavController.navigate(destination: String, bundle: Bundle) {
    this.currentBackStackEntry?.arguments?.putAll(bundle)
    this.navigate(destination)
}

fun NavHostController.navigate(destination: String, bundle: Bundle) {
    this.currentBackStackEntry?.arguments?.putAll(bundle)
    this.navigate(destination)
}