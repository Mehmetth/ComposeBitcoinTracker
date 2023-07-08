package com.example.composebitcointracker.data

import javax.inject.Inject

class BitcoinTrackerDataSource @Inject constructor(private val bitcoinTrackerService: BitcoinTrackerService) {
    suspend fun getBitcoins() = bitcoinTrackerService.getBitcoins()
    suspend fun getBitcoinDetail(coinId: String) = bitcoinTrackerService.getBitcoinDetail(coinId)
}