package com.example.webviewapp.di

import com.example.webviewapp.data.repository.FirebaseRepositoryImpl
import com.example.webviewapp.domain.repository.FirebaseRepository
import com.example.webviewapp.data.database.dao.LeaderboardDao
import com.example.webviewapp.data.repository.DatabaseRepositoryImpl
import com.example.webviewapp.domain.mapper.LeaderboardMapper
import com.example.webviewapp.domain.repository.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesFirebaseRepository(
    ): FirebaseRepository {
        return FirebaseRepositoryImpl()
    }

    @Provides
    @Singleton
    fun providesDatabaseRepository(
        dao: LeaderboardDao,
        mapper: LeaderboardMapper
    ): DatabaseRepository {
        return DatabaseRepositoryImpl(dao, mapper)
    }
}