package com.example.composebitcointracker.app.di

import com.example.composebitcointracker.data.BitcoinTrackerDataSource
import com.example.composebitcointracker.data.BitcoinTrackerService
import com.example.composebitcointracker.data.repository.BitcoinTrackerRepositoryImpl
import com.example.composebitcointracker.domain.repository.BitcoinTrackerRepository
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
    fun provideBitcoinTrackerDataSource(
        bitcoinTrackerService: BitcoinTrackerService,
    ): BitcoinTrackerDataSource = BitcoinTrackerDataSource(bitcoinTrackerService)

    @Provides
    @Singleton
    fun provideBitcoinTrackerRepository(
        bitcoinTrackerDataSource: BitcoinTrackerDataSource,
    ): BitcoinTrackerRepository = BitcoinTrackerRepositoryImpl(bitcoinTrackerDataSource)
}