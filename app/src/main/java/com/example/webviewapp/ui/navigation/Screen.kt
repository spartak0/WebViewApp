package com.example.webviewapp.ui.navigation

sealed class Screen(val route:String){

    object MainScreen:Screen("main_screen")
    object GameScreen:Screen("game_screen")
    object NoInternetScreen:Screen("no_internet_screen")
    object WebViewScreen:Screen("web_view_screen")
}
