package com.example.composecointracker.data

import javax.inject.Inject

class CoinTrackerDataSource @Inject constructor(private val coinTrackerService: CoinTrackerService) {
    suspend fun getCoins() = coinTrackerService.getCoins()
    suspend fun getCoinDetail(coinId: String) = coinTrackerService.getCoinDetail(coinId)
}