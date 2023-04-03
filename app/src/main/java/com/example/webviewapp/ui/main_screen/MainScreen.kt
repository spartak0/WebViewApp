package com.example.webviewapp.ui.main_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.webviewapp.ui.data.FirebaseRepositoryImpl
import com.example.webviewapp.ui.domain.Result

@Composable
fun MainScreen(
    navigateToNoInternet: () -> Unit,
    navigateToGame: () -> Unit,
    navigateToWebView: (String) -> Unit,
    viewModel: MainScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = MyViewModelFactory(
            FirebaseRepositoryImpl(),
            LocalContext.current.applicationContext
        )
    )
) {
    val url = viewModel.url.collectAsState()
    LaunchedEffect(key1 = url.value){
        when (url.value) {
            is Result.Success -> {
                if (url.value.data == "") navigateToGame()
                else navigateToWebView(url.value.data!!)
            }
            is Result.Error -> {
                navigateToNoInternet()
            }
            else -> {}
        }
    }

}