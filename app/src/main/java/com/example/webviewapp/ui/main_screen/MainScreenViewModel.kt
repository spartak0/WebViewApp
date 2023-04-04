package com.example.webviewapp.ui.main_screen

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webviewapp.BuildConfig
import com.example.webviewapp.domain.repository.FirebaseRepository
import com.example.webviewapp.domain.model.Result
import com.example.webviewapp.utils.Constants
import com.example.webviewapp.utils.SharedPrefHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: FirebaseRepository,
    private val sharedPrefHandler: SharedPrefHandler
) : ViewModel() {
    private var _url = MutableStateFlow<Result<String>>(Result.Loading())
    val url = _url.asStateFlow()

    init {
        fetchUrl()
    }

    private fun fetchUrl() {
        viewModelScope.launch(Dispatchers.IO) {
            val url = sharedPrefHandler.getString(Constants.URL)
            if (url == null) {
                repository.fetchUrl().collect {
                    _url.value = it
                }
            } else _url.value = Result.Success(url)
        }
    }

    fun checkIsEmu(): Boolean {
        if (BuildConfig.DEBUG) return false
        val phoneModel = Build.MODEL
        val buildProduct = Build.PRODUCT
        val buildHardware = Build.HARDWARE
        val buildBrand = Build.BRAND
        return ((Build.FINGERPRINT.startsWith("generic")
                || phoneModel.contains("google_sdk")
                || phoneModel.lowercase(Locale.getDefault()).contains("droid4x")
                || phoneModel.contains("Emulator")
                || phoneModel.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || buildHardware == "goldfish"
                || buildBrand.contains("google")
                || buildHardware == "vbox86"
                || buildProduct == "sdk"
                || buildProduct == "google_sdk"
                || buildProduct == "sdk_x86"
                || buildProduct == "vbox86p"
                || Build.BOARD.lowercase(Locale.getDefault()).contains("nox")
                || Build.BOOTLOADER.lowercase(Locale.getDefault()).contains("nox")
                || buildHardware.lowercase(Locale.getDefault()).contains("nox")
                || buildProduct.lowercase(Locale.getDefault()).contains("nox"))
                || buildBrand.startsWith("generic") && Build.DEVICE.startsWith("generic"))
    }
}