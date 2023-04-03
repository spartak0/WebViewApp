package com.example.webviewapp.ui.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.webviewapp.ui.data.FirebaseRepositoryImpl
import com.example.webviewapp.ui.domain.NetworkResult
import com.example.webviewapp.ui.navigation.NavGraph
import com.example.webviewapp.ui.navigation.Screen
import com.example.webviewapp.ui.theme.WebViewAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainActivityViewModel = MainActivityViewModel(FirebaseRepositoryImpl())
        setContent {
            WebViewAppTheme {
                val url = viewModel.url.collectAsState()
                val startDestination = when (url.value) {
                    is NetworkResult.Success -> {
                        if (url.value.data == "") Screen.GameScreen.route
                        else Screen.MainScreen.route
                    }
                    is NetworkResult.Loading -> Screen.NoInternetScreen.route
                    is NetworkResult.Error -> Screen.NoInternetScreen.route
                }
                NavGraph(startDestination = startDestination)
            }
        }
    }
}