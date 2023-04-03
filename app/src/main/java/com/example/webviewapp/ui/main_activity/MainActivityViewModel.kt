package com.example.webviewapp.ui.main_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webviewapp.ui.domain.FirebaseRepository
import com.example.webviewapp.ui.domain.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: FirebaseRepository) : ViewModel() {
    val a = "123"
    private var _url = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())
    val url = _url.asStateFlow()

    init {
        viewModelScope.launch {
            fetchUrl()
        }
    }

    private suspend fun fetchUrl() {
        repository.fetchUrl().collect {
            _url.value = it
        }
    }
}