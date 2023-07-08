package com.example.composebitcointracker.domain.repository

import com.example.composebitcointracker.data.model.Bitcoin
import com.example.composebitcointracker.data.model.BitcoinDetail
import com.example.composebitcointracker.data.model.Resource
import kotlinx.coroutines.flow.Flow

interface BitcoinTrackerRepository {
    fun getBitcoins(): Flow<Resource<List<Bitcoin>>>
    fun getBitcoinDetail(coinId: String): Flow<Resource<BitcoinDetail>>
}