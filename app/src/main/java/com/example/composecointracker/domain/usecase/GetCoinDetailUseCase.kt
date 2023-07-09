package com.example.composecointracker.domain.usecase

import com.example.composecointracker.data.model.CoinDetail
import com.example.composecointracker.data.model.Resource
import com.example.composecointracker.domain.repository.CoinTrackerRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val coinTrackerRepository: CoinTrackerRepository
) {
    operator fun invoke(coinId: String): Flow<GetCoinDetailState> = callbackFlow {
        coinTrackerRepository.getCoinDetail(coinId).collect { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        trySend(
                            GetCoinDetailState.Data(it)
                        )
                    } ?: kotlin.run {
                        trySend(
                            GetCoinDetailState.EmptyData
                        )
                    }
                }

                is Resource.Error,
                is Resource.Fail -> {
                    trySend(GetCoinDetailState.Error(result.message))
                }
            }
        }
        awaitClose { cancel() }
    }

    sealed interface GetCoinDetailState {
        data class Data(val coinDetail: CoinDetail) : GetCoinDetailState
        data class Error(val throwable: Throwable?) : GetCoinDetailState
        object EmptyData : GetCoinDetailState
    }
}