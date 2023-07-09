package com.example.composebitcointracker.features.screen.favorite

import com.example.composebitcointracker.features.base.BaseViewModel
import com.example.composebitcointracker.features.base.IEffect
import com.example.composebitcointracker.features.base.IEvent
import com.example.composebitcointracker.features.base.IState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BitcoinFavoriteViewModel @Inject constructor(
) : BaseViewModel<BitcoinFavoriteState, BitcoinFavoriteEvent, BitcoinFavoriteEffect>() {

    override fun setInitialState() = BitcoinFavoriteState()

    override fun handleEvents(event: BitcoinFavoriteEvent) {
        when (event) {
            else -> {}
        }
    }
}


data class BitcoinFavoriteState(
    val isLoading: Boolean = true,
) : IState

sealed interface BitcoinFavoriteEffect : IEffect {
    data class ShowError(val message: String) : BitcoinFavoriteEffect
}

sealed interface BitcoinFavoriteEvent : IEvent {
}