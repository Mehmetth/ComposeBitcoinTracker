package com.example.composebitcointracker.domain.usecase

import com.example.composebitcointracker.data.model.Bitcoin
import com.example.composebitcointracker.data.model.Resource
import com.example.composebitcointracker.domain.repository.BitcoinTrackerRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class GetBitcoinsUseCase @Inject constructor(private val bitcoinTrackerRepository: BitcoinTrackerRepository) {
    operator fun invoke(): Flow<GetBitcoinsState> = callbackFlow {
        bitcoinTrackerRepository.getBitcoins().collect { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        if (it.isNotEmpty()) {
                            trySend(
                                GetBitcoinsState.Data(it)
                            )
                        } else {
                            trySend(
                                GetBitcoinsState.EmptyData
                            )
                        }
                    } ?: kotlin.run {
                        trySend(
                            GetBitcoinsState.EmptyData
                        )
                    }
                }

                is Resource.Error,
                is Resource.Fail -> {
                    trySend(GetBitcoinsState.Error(result.message))
                }
            }
        }
        awaitClose { cancel() }
    }

    sealed interface GetBitcoinsState {
        data class Data(val bitcoins: List<Bitcoin>) : GetBitcoinsState
        data class Error(val throwable: Throwable?) : GetBitcoinsState
        object EmptyData : GetBitcoinsState
    }
}