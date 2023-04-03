package com.example.webviewapp.ui.domain

import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {
    suspend fun fetchUrl(): Flow<NetworkResult<String>>
}