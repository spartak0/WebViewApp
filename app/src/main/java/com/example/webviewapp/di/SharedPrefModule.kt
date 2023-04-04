package com.example.webviewapp.di

import android.content.Context
import com.example.webviewapp.utils.SharedPrefHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefModule {
    @Provides
    @Singleton
    fun providesSharedPref(@ApplicationContext context: Context): SharedPrefHandler =
        SharedPrefHandler(context)
}