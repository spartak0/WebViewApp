package com.example.webviewapp.ui.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.webviewapp.ui.navigation.NavGraph
import com.example.webviewapp.ui.navigation.Screen
import com.example.webviewapp.ui.theme.WebViewAppTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme{
                NavGraph(startDestination = Screen.MainScreen.route)
            }
        }
    }
}