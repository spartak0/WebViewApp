package com.example.webviewapp.ui.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.webviewapp.domain.model.Result

@Composable
fun MainScreen(
    navigateToNoInternet: () -> Unit,
    navigateToGame: () -> Unit,
    navigateToWebView: (String) -> Unit,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val url = viewModel.url.collectAsState()
    IsLoadingContent()
    LaunchedEffect(key1 = true) {
        if (viewModel.checkIsEmu()) navigateToNoInternet()
    }
    LaunchedEffect(key1 = url.value) {
        when (url.value) {
            is Result.Success -> {
                if (url.value.data == "") navigateToGame()
                else navigateToWebView(url.value.data!!)
            }
            is Result.Error -> {
                navigateToNoInternet()
            }
            is Result.Loading -> {
            }
        }
    }
}

@Composable
fun IsLoadingContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(Modifier.size(75.dp))
    }
}
