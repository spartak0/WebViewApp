package com.example.webviewapp.ui.data

import com.example.webviewapp.ui.domain.FirebaseRepository
import com.example.webviewapp.ui.domain.NetworkResult
import com.example.webviewapp.ui.utils.Constants
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseRepositoryImpl : FirebaseRepository {
    private val remoteConfig = Firebase.remoteConfig

    init {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
    }

    override suspend fun fetchUrl(): Flow<NetworkResult<String>> = flow {
        emit(NetworkResult.Loading())
        try {
            remoteConfig.fetchAndActivate().await()
            emit(NetworkResult.Success(remoteConfig.getString(Constants.URL)))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message))
        }
    }
}