package com.example.composecointracker.features.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.composecointracker.data.model.CoinDetail
import com.example.composecointracker.domain.usecase.GetCoinDetailUseCase
import com.example.composecointracker.features.base.BaseViewModel
import com.example.composecointracker.features.base.IEffect
import com.example.composecointracker.features.base.IEvent
import com.example.composecointracker.features.base.IState
import com.example.composecointracker.features.navigation.CoinDetailArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCoinDetailUseCase: GetCoinDetailUseCase
) : BaseViewModel<CoinDetailState, CoinDetailEvent, CoinDetailEffect>() {

    private val args = CoinDetailArgs(savedStateHandle)

    init {
        getCoinDetail(args.coinId)
    }

    override fun setInitialState() = CoinDetailState()

    override fun handleEvents(event: CoinDetailEvent) {
        when (event) {
            else -> {}
        }
    }

    private fun getCoinDetail(coinId: String) {
        viewModelScope.launch {
            getCoinDetailUseCase.invoke(coinId).collect {
                setState { copy(isLoading = true) }
                when (it) {
                    is GetCoinDetailUseCase.GetCoinDetailState.Data -> {
                        setState { copy(isLoading = false, coinDetail = it.coinDetail) }
                    }

                    is GetCoinDetailUseCase.GetCoinDetailState.EmptyData -> {
                        setState { copy(isLoading = false, coinDetail = null) }
                    }

                    is GetCoinDetailUseCase.GetCoinDetailState.Error -> {
                        setState { copy(isLoading = false, coinDetail = null) }
                        //TODO
                        //Handle service error or fail
                    }
                }
            }
        }
    }
}


data class CoinDetailState(
    val isLoading: Boolean = true,
    val coinDetail: CoinDetail? = null
) : IState

sealed interface CoinDetailEffect : IEffect {
    data class ShowError(val message: String) : CoinDetailEffect
}

sealed interface CoinDetailEvent : IEvent {
}