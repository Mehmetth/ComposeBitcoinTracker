package com.example.composecointracker.features.screen.list

import androidx.lifecycle.viewModelScope
import com.example.composecointracker.data.model.Coin
import com.example.composecointracker.domain.usecase.GetCoinsUseCase
import com.example.composecointracker.features.base.BaseViewModel
import com.example.composecointracker.features.base.IEffect
import com.example.composecointracker.features.base.IEvent
import com.example.composecointracker.features.base.IState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : BaseViewModel<CoinListState, CoinListEvent, CoinListEffect>() {

    init {
        getCoins()
    }

    override fun setInitialState() = CoinListState()

    override fun handleEvents(event: CoinListEvent) {
        when (event) {
            is CoinListEvent.OnSearchUser -> {
                if (event.searchQuery.length > 2) {
                    setState {
                        copy(isLoading = true)
                    }
                    searchCoin(event.searchQuery)
                } else {
                    setState {
                        copy(
                            isLoading = false,
                            filteredCoins = emptyList()
                        )
                    }
                }
            }

            is CoinListEvent.OnDetailClick -> {
                setEffect { CoinListEffect.NavigateToDetail(event.coinId) }
            }
        }
    }

    private fun searchCoin(searchQuery: String) {
        getCurrentState().coins.filter { it.name?.lowercase()?.contains(searchQuery) == true }
            .apply {
                setState { copy(isLoading = false, filteredCoins = this@apply) }
            }
    }

    private fun getCoins() {
        viewModelScope.launch {
            getCoinsUseCase.invoke().collect {
                setState { copy(isLoading = true) }
                when (it) {
                    is GetCoinsUseCase.GetCoinsState.Data -> {
                        setState { copy(isLoading = false, coins = it.coins) }
                    }

                    is GetCoinsUseCase.GetCoinsState.EmptyData -> {
                        setState { copy(isLoading = false, coins = emptyList()) }
                    }

                    is GetCoinsUseCase.GetCoinsState.Error -> {
                        setState { copy(isLoading = false, coins = emptyList()) }
                        //TODO
                        //Handle service error or fail
                    }
                }
            }
        }
    }
}

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val filteredCoins: List<Coin> = emptyList()
) : IState

sealed interface CoinListEffect : IEffect {
    data class ShowError(val message: String) : CoinListEffect
    data class NavigateToDetail(val coinId: String) : CoinListEffect
}

sealed interface CoinListEvent : IEvent {
    data class OnSearchUser(val searchQuery: String) :
        CoinListEvent

    data class OnDetailClick(val coinId: String) :
        CoinListEvent
}