package com.example.webviewapp.ui.navigation

sealed class Screen(val route:String){
    //main
    object MainScreen:Screen("main_screen")
    object MiniGameScreen:Screen("mini_game_screen")
    object NoInternetScreen:Screen("no_internet_screen")
    object WebViewScreen:Screen("web_view_screen")


    //mini_game
    object MenuGameScreen : Screen("menu_game_screen")
    object GameScreen : Screen("game_screen")
    object ScoreScreen : Screen("score_screen")
    object LeaderboardScreen : Screen("leaderboard_screen")


}
