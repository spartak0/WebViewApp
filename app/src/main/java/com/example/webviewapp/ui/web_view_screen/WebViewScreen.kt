package com.example.webviewapp.ui.web_view_screen

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun WebViewScreen(
    url: String,
    navigateToNoInternet: () -> Unit,
    viewModel: WebScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current.applicationContext
    WebViewPage(url) {
        if (!viewModel.checkInternet(context)) {
            navigateToNoInternet()
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewPage(url: String, onError: () -> Unit) {
    val state = rememberWebViewState(url)
    WebView(
        state = state,
        modifier = Modifier.fillMaxSize(),
        onCreated = {
            it.settings.javaScriptEnabled = true
            it.settings.loadWithOverviewMode = true
            it.settings.useWideViewPort = true
            it.settings.domStorageEnabled = true
            it.settings.databaseEnabled = true
            it.settings.setSupportZoom(false)
            it.settings.allowFileAccess = true
            it.settings.allowContentAccess = true
        },
        client = remember {
            object : AccompanistWebViewClient() {
                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    Log.d("AAA", "onReceivedError: ошибкаЁ!!!")
                    onError()
                }
            }
        })
}



