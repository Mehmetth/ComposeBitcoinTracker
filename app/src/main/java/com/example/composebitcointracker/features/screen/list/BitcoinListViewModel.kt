package com.example.composebitcointracker.features.screen.list

import com.example.composebitcointracker.features.base.BaseViewModel
import com.example.composebitcointracker.features.base.IEffect
import com.example.composebitcointracker.features.base.IEvent
import com.example.composebitcointracker.features.base.IState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BitcoinListViewModel @Inject constructor(
) : BaseViewModel<BitcoinListState, BitcoinListEvent, BitcoinListEffect>() {

    override fun setInitialState() = BitcoinListState()

    override fun handleEvents(event: BitcoinListEvent) {
        when (event) {
            else -> {}
        }
    }
}


data class BitcoinListState(
    val isLoading: Boolean = true,
) : IState

sealed interface BitcoinListEffect : IEffect {
    data class ShowError(val message: String) : BitcoinListEffect
}

sealed interface BitcoinListEvent : IEvent {
}