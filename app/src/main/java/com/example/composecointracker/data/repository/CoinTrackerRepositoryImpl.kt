package com.example.composecointracker.data.repository

import com.example.composecointracker.data.CoinTrackerDataSource
import com.example.composecointracker.data.model.Coin
import com.example.composecointracker.data.model.CoinDetail
import com.example.composecointracker.data.model.Resource
import com.example.composecointracker.domain.repository.CoinTrackerRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class CoinTrackerRepositoryImpl @Inject constructor(private val coinTrackerDataSource: CoinTrackerDataSource) :
    CoinTrackerRepository {

    override fun getCoins(): Flow<Resource<List<Coin>>> =
        callbackFlow {
            val response = coinTrackerDataSource.getCoins()
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

    override fun getCoinDetail(coinId: String): Flow<Resource<CoinDetail>> =
        callbackFlow {
            val response = coinTrackerDataSource.getCoinDetail(coinId)
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