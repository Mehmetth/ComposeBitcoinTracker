package com.example.composecointracker.domain.usecase

import com.example.composecointracker.data.model.Coin
import com.example.composecointracker.data.model.Resource
import com.example.composecointracker.domain.repository.CoinTrackerRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val coinTrackerRepository: CoinTrackerRepository) {
    operator fun invoke(): Flow<GetCoinsState> = callbackFlow {
        coinTrackerRepository.getCoins().collect { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        if (it.isNotEmpty()) {
                            trySend(
                                GetCoinsState.Data(it)
                            )
                        } else {
                            trySend(
                                GetCoinsState.EmptyData
                            )
                        }
                    } ?: kotlin.run {
                        trySend(
                            GetCoinsState.EmptyData
                        )
                    }
                }

                is Resource.Error,
                is Resource.Fail -> {
                    trySend(GetCoinsState.Error(result.message))
                }
            }
        }
        awaitClose { cancel() }
    }

    sealed interface GetCoinsState {
        data class Data(val coins: List<Coin>) : GetCoinsState
        data class Error(val throwable: Throwable?) : GetCoinsState
        object EmptyData : GetCoinsState
    }
}