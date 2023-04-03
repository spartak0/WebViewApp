package com.example.webviewapp.ui.main_screen

import android.annotation.SuppressLint
import android.content.Context
import android.net.Network
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.webviewapp.ui.domain.FirebaseRepository
import com.example.webviewapp.ui.domain.Result
import com.example.webviewapp.ui.utils.Constants
import com.example.webviewapp.ui.utils.SharedPrefHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val repository: FirebaseRepository,
    context: Context
) : ViewModel() {
    private var _url = MutableStateFlow<Result<String>>(Result.Loading())
    val url = _url.asStateFlow()

    init {
        fetchUrl(context)
    }
    fun fetchUrl(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchUrl().collect {
                _url.value = it
            }
//            val url = SharedPrefHandler(context).getString(Constants.URL)
//            if (url == null) {
//                repository.fetchUrl().collect {
//                    _url.value = it
//                }
//            } else _url.value = Result.Success(url)
        }
    }
}

class MyViewModelFactory(private val repository:FirebaseRepository, private val context: Context) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MainScreenViewModel(repository, context) as T
}