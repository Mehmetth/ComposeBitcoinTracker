package com.example.composebitcointracker.domain.usecase

import com.example.composebitcointracker.data.model.BitcoinDetail
import com.example.composebitcointracker.data.model.Resource
import com.example.composebitcointracker.domain.repository.BitcoinTrackerRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class GetBitcoinDetailUseCase @Inject constructor(
    private val bitcoinTrackerRepository: BitcoinTrackerRepository
) {
    operator fun invoke(coinId: String): Flow<GetBitcoinDetailState> = callbackFlow {
        bitcoinTrackerRepository.getBitcoinDetail(coinId).collect { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        trySend(
                            GetBitcoinDetailState.Data(it)
                        )
                    } ?: kotlin.run {
                        trySend(
                            GetBitcoinDetailState.EmptyData
                        )
                    }
                }

                is Resource.Error,
                is Resource.Fail -> {
                    trySend(GetBitcoinDetailState.Error(result.message))
                }
            }
        }
        awaitClose { cancel() }
    }

    sealed interface GetBitcoinDetailState {
        data class Data(val bitcoinDetail: BitcoinDetail) : GetBitcoinDetailState
        data class Error(val throwable: Throwable?) : GetBitcoinDetailState
        object EmptyData : GetBitcoinDetailState
    }
}