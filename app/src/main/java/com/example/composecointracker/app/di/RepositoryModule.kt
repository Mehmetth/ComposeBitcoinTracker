package com.example.composecointracker.app.di

import com.example.composecointracker.data.CoinTrackerDataSource
import com.example.composecointracker.data.CoinTrackerService
import com.example.composecointracker.data.repository.CoinTrackerRepositoryImpl
import com.example.composecointracker.domain.repository.CoinTrackerRepository
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
    fun provideCoinTrackerDataSource(
        coinTrackerService: CoinTrackerService,
    ): CoinTrackerDataSource = CoinTrackerDataSource(coinTrackerService)

    @Provides
    @Singleton
    fun provideCoinTrackerRepository(
        coinTrackerDataSource: CoinTrackerDataSource,
    ): CoinTrackerRepository = CoinTrackerRepositoryImpl(coinTrackerDataSource)
}