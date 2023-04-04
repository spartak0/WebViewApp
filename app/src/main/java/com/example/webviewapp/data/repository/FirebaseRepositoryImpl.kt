package com.example.webviewapp.data.repository

import android.util.Log
import com.example.webviewapp.domain.repository.FirebaseRepository
import com.example.webviewapp.domain.model.Result
import com.example.webviewapp.utils.Constants
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

    override suspend fun fetchUrl(): Flow<Result<String>> = flow {
        emit(Result.Loading())
        try {
            remoteConfig.fetchAndActivate().await()
            emit(Result.Success(remoteConfig.getString(Constants.URL)))
        } catch (e: Exception) {
            Log.d("AAA", "fetchUrl: ${e.message}")
            emit(Result.Error(e.message))
        }
    }
}