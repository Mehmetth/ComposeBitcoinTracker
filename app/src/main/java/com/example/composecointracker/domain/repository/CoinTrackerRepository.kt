package com.example.composecointracker.domain.repository

import com.example.composecointracker.data.model.Coin
import com.example.composecointracker.data.model.CoinDetail
import com.example.composecointracker.data.model.Resource
import kotlinx.coroutines.flow.Flow

interface CoinTrackerRepository {
    fun getCoins(): Flow<Resource<List<Coin>>>
    fun getCoinDetail(coinId: String): Flow<Resource<CoinDetail>>
}