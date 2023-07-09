package com.example.composecointracker.features.screen.detail

import androidx.lifecycle.SavedStateHandle
import com.example.composecointracker.features.base.BaseViewModel
import com.example.composecointracker.features.base.IEffect
import com.example.composecointracker.features.base.IEvent
import com.example.composecointracker.features.base.IState
import com.example.composecointracker.features.navigation.CoinDetailArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<CoinDetailState, CoinDetailEvent, CoinDetailEffect>() {

    private val args = CoinDetailArgs(savedStateHandle)

    init {
        args.coinId
    }

    override fun setInitialState() = CoinDetailState()

    override fun handleEvents(event: CoinDetailEvent) {
        when (event) {
            else -> {}
        }
    }
}


data class CoinDetailState(
    val isLoading: Boolean = true,
) : IState

sealed interface CoinDetailEffect : IEffect {
    data class ShowError(val message: String) : CoinDetailEffect
}

sealed interface CoinDetailEvent : IEvent {
}