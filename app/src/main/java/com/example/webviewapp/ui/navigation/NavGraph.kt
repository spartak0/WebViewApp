package com.example.webviewapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.webviewapp.ui.main_screen.MainScreen
import com.example.webviewapp.ui.mini_game_screen.MenuGameScreen
import com.example.webviewapp.ui.mini_game_screen.game_screen.GameScreen
import com.example.webviewapp.ui.mini_game_screen.leaderboard_screen.LeaderboardScreen
import com.example.webviewapp.ui.mini_game_screen.score_screen.ScoreScreen
import com.example.webviewapp.ui.mini_game_screen.score_screen.ScoreViewModel
import com.example.webviewapp.ui.no_internet_screen.NoInternetScreen
import com.example.webviewapp.ui.web_view_screen.WebViewScreen
import com.example.webviewapp.utils.Constants
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
                navigateToNoInternet = {
                    navController.navigate(Screen.NoInternetScreen.route) {
                        popUpTo(
                            0
                        )
                    }
                },
                navigateToGame = { navController.navigate(Screen.MiniGameScreen.route) { popUpTo(0) } },
                navigateToWebView = { url ->
                    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                    navController.navigate(Screen.WebViewScreen.route + "/$encodedUrl") {
                        popUpTo(0)
                    }
                })
        }
        composable(Screen.NoInternetScreen.route) {
            NoInternetScreen(navigateToMain = { navController.navigate(Screen.MainScreen.route) })
        }
        composable(Screen.WebViewScreen.route + "/{${Constants.URL}}") {
            WebViewScreen(it.arguments?.getString(Constants.URL) ?: "",
                navigateToNoInternet = {
                    navController.navigate(Screen.NoInternetScreen.route) {
                        popUpTo(0)
                    }
                })
        }
        miniGameGraph(navController)
    }

}

fun NavGraphBuilder.miniGameGraph(navController: NavController) {
    navigation(
        startDestination = Screen.MenuGameScreen.route,
        route = Screen.MiniGameScreen.route
    ) {
        composable(Screen.MenuGameScreen.route) {
            MenuGameScreen(navigateToGame = {
                navController.navigate(Screen.GameScreen.route)
            },
                navigateToLeaderboard = {
                    navController.navigate(Screen.LeaderboardScreen.route)
                })
        }
        composable(
            Screen.GameScreen.route
        ) {
            GameScreen(
                navigateUp = { navController.navigateUp() },
                navigateToScore = {
                    navController.navigate("${Screen.ScoreScreen.route}/$it") {
                        popUpTo(Screen.MenuGameScreen.route)
                    }
                })
        }
        composable("${Screen.ScoreScreen.route}/{${Constants.SCORE_ARGUMENT}}",
            arguments = listOf(
                navArgument(Constants.SCORE_ARGUMENT) {
                    type = NavType.IntType
                }
            )) {
            val viewModel: ScoreViewModel = hiltViewModel()
            ScoreScreen(
                viewModel,
                score = it.arguments?.getInt(Constants.SCORE_ARGUMENT) ?: 0,
                navigateToGame = {
                    navController.navigate(Screen.GameScreen.route) {
                        popUpTo(Screen.MenuGameScreen.route)
                    }
                },
                navigateToMenu = {
                    navController.navigate(Screen.MenuGameScreen.route) {
                        popUpTo(0)
                    }
                },
                navigateToLeaderboard = {
                    navController.navigate(Screen.LeaderboardScreen.route)
                })
        }
        composable(
            Screen.LeaderboardScreen.route
        ) {
            LeaderboardScreen(navigateUp = { navController.navigateUp() })
        }
    }
}