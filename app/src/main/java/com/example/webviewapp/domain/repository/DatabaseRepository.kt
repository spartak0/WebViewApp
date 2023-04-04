package com.example.webviewapp.domain.repository

import com.example.webviewapp.domain.model.LeaderboardData
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    suspend fun fetchTopTenLeaderboard(): Flow<List<LeaderboardData>>
    suspend fun fetchAllLeaderboard(): Flow<List<LeaderboardData>>
    suspend fun clearLeaderboard()
    suspend fun insertLeader(leaderboardData: LeaderboardData)
}