package com.example.webviewapp.ui.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.webviewapp.ui.data.FirebaseRepositoryImpl
import com.example.webviewapp.ui.domain.NetworkResult
import com.example.webviewapp.ui.theme.WebViewAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var text by remember {
                mutableStateOf("")
            }
            val scope = rememberCoroutineScope()
            WebViewAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    scope.launch {
                        FirebaseRepositoryImpl().fetchUrl().collect {
                            when (it) {
                                is NetworkResult.Success -> text = it.data!!
                                is NetworkResult.Error -> text = it.message!!
                                is NetworkResult.Loading -> {}
                            }
                        }
                    }

                    Text(text = if (text != "") text else "пусто")
                }
            }
        }
    }
}