package com.example.composebitcointracker.data.repository

import com.example.composebitcointracker.data.BitcoinTrackerDataSource
import com.example.composebitcointracker.data.model.Bitcoin
import com.example.composebitcointracker.data.model.BitcoinDetail
import com.example.composebitcointracker.data.model.Resource
import com.example.composebitcointracker.domain.repository.BitcoinTrackerRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class BitcoinTrackerRepositoryImpl @Inject constructor(private val bitcoinTrackerDataSource: BitcoinTrackerDataSource) :
    BitcoinTrackerRepository {

    override fun getBitcoins(): Flow<Resource<List<Bitcoin>>> =
        callbackFlow {
            val response = bitcoinTrackerDataSource.getBitcoins()
            if (response.isSuccessful) {
                response.body()?.let {
                    trySend(Resource.Success(it))
                } ?: kotlin.run {
                    trySend(Resource.Fail(null))
                }
            } else {
                trySend(Resource.Error(null))
            }
            awaitClose { cancel() }
        }

    override fun getBitcoinDetail(coinId: String): Flow<Resource<BitcoinDetail>> =
        callbackFlow {
            val response = bitcoinTrackerDataSource.getBitcoinDetail(coinId)
            if (response.isSuccessful) {
                response.body()?.let {
                    trySend(Resource.Success(it))
                } ?: kotlin.run {
                    trySend(Resource.Fail(null))
                }
            } else {
                trySend(Resource.Error(null))
            }
            awaitClose { cancel() }
        }
}