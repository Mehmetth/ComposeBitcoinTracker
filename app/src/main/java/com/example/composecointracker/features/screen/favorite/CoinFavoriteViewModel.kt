package com.example.composecointracker.features.screen.favorite

import com.example.composecointracker.features.base.BaseViewModel
import com.example.composecointracker.features.base.IEffect
import com.example.composecointracker.features.base.IEvent
import com.example.composecointracker.features.base.IState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinFavoriteViewModel @Inject constructor(
) : BaseViewModel<CoinFavoriteState, CoinFavoriteEvent, CoinFavoriteEffect>() {

    override fun setInitialState() = CoinFavoriteState()

    override fun handleEvents(event: CoinFavoriteEvent) {
        when (event) {
            else -> {}
        }
    }
}


data class CoinFavoriteState(
    val isLoading: Boolean = true,
) : IState

sealed interface CoinFavoriteEffect : IEffect {
    data class ShowError(val message: String) : CoinFavoriteEffect
}

sealed interface CoinFavoriteEvent : IEvent {
}