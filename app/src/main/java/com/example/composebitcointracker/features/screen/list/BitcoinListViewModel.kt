package com.example.composebitcointracker.features.screen.list

import androidx.lifecycle.viewModelScope
import com.example.composebitcointracker.data.model.Bitcoin
import com.example.composebitcointracker.domain.usecase.GetBitcoinsUseCase
import com.example.composebitcointracker.features.base.BaseViewModel
import com.example.composebitcointracker.features.base.IEffect
import com.example.composebitcointracker.features.base.IEvent
import com.example.composebitcointracker.features.base.IState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BitcoinListViewModel @Inject constructor(
    private val getBitcoinsUseCase: GetBitcoinsUseCase
) : BaseViewModel<BitcoinListState, BitcoinListEvent, BitcoinListEffect>() {

    init {
        getBitcoins()
    }

    override fun setInitialState() = BitcoinListState()

    override fun handleEvents(event: BitcoinListEvent) {
        when (event) {
            is BitcoinListEvent.OnSearchUser -> {
                if (event.searchQuery.length > 2) {
                    setState {
                        copy(isLoading = true)
                    }
                    searchBitcoin(event.searchQuery)
                } else {
                    setState {
                        copy(
                            isLoading = false,
                            filteredBitcoins = emptyList()
                        )
                    }
                }
            }
        }
    }

    private fun searchBitcoin(searchQuery: String) {
        getCurrentState().bitcoins.filter { it.name?.lowercase()?.contains(searchQuery) == true }
            .apply {
                setState { copy(isLoading = false, filteredBitcoins = this@apply) }
            }
    }

    private fun getBitcoins() {
        viewModelScope.launch {
            getBitcoinsUseCase.invoke().collect {
                setState { copy(isLoading = true) }
                when (it) {
                    is GetBitcoinsUseCase.GetBitcoinsState.Data -> {
                        setState { copy(isLoading = false, bitcoins = it.bitcoins) }
                    }

                    is GetBitcoinsUseCase.GetBitcoinsState.EmptyData -> {
                        setState { copy(isLoading = false, bitcoins = emptyList()) }
                    }

                    is GetBitcoinsUseCase.GetBitcoinsState.Error -> {
                        setState { copy(isLoading = false, bitcoins = emptyList()) }
                        //TODO
                        //Handle service error or fail
                    }
                }
            }
        }
    }
}

data class BitcoinListState(
    val isLoading: Boolean = false,
    val bitcoins: List<Bitcoin> = emptyList(),
    val filteredBitcoins: List<Bitcoin> = emptyList()
) : IState

sealed interface BitcoinListEffect : IEffect {
    data class ShowError(val message: String) : BitcoinListEffect
}

sealed interface BitcoinListEvent : IEvent {
    data class OnSearchUser(val searchQuery: String) :
        BitcoinListEvent
}