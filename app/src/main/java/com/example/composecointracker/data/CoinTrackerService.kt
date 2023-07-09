package com.example.composecointracker.data

import com.example.composecointracker.common.Constants
import com.example.composecointracker.data.model.Coin
import com.example.composecointracker.data.model.CoinDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinTrackerService {
    @GET("markets")
    suspend fun getCoins(
        @Query("vs_currency") vs_currency: String = Constants.VS_CURRENCY
    ): Response<List<Coin>>

    @GET("{coinId}")
    suspend fun getCoinDetail(
        @Path("coinId") coinId: String
    ): Response<CoinDetail>
}