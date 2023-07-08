package com.example.composebitcointracker.data

import com.example.composebitcointracker.common.Constants
import com.example.composebitcointracker.data.model.Bitcoin
import com.example.composebitcointracker.data.model.BitcoinDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BitcoinTrackerService {
    @GET("markets")
    suspend fun getBitcoins(
        @Query("vs_currency") vs_currency: String = Constants.VS_CURRENCY
    ): Response<List<Bitcoin>>

    @GET("{coinId}")
    suspend fun getBitcoinDetail(
        @Path("coinId") coinId: String
    ): Response<BitcoinDetail>
}