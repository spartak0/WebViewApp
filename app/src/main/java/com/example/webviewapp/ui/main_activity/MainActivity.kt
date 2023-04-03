package com.example.webviewapp.ui.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.webviewapp.ui.navigation.NavGraph
import com.example.webviewapp.ui.navigation.Screen
import com.example.webviewapp.ui.theme.WebViewAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebViewAppTheme {
                NavGraph(startDestination = Screen.MainScreen.route)
            }
        }
    }
}