package com.example.webviewapp.domain.repository

import com.example.webviewapp.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {
    suspend fun fetchUrl(): Flow<Result<String>>
}