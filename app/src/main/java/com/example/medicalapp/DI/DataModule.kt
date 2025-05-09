package com.example.medicalapp.DI

import android.content.Context
import com.example.medicalapp.Data.Repo.repo
import com.example.medicalapp.user_praf.UserPreferencesManager

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideRepo() = repo()


    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context) = context

    @Singleton
    @Provides
    fun providePrefManager(
        @ApplicationContext context: Context
    ) = UserPreferencesManager(context)
}