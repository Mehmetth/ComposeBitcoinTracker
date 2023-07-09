package com.example.composecointracker.features.screen.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composecointracker.R
import com.example.composecointracker.features.component.CoinTrackerCardItem
import com.example.composecointracker.features.component.CoinTrackerLoadingContent
import com.example.composecointracker.features.component.CoinTrackerSearchBar

@Composable
fun CoinListRoute(
    viewModel: CoinListViewModel = hiltViewModel(),
    navigateToDetail: (String) -> Unit
) {
    val viewState = viewModel.state.collectAsState().value

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is CoinListEffect.NavigateToDetail -> {
                    navigateToDetail(effect.coinId)
                }

                else -> {}
            }
        }
    }

    CoinListScreen(
        viewState,
        onViewEvent = viewModel::setEvent
    )
}

@Composable
private fun CoinListScreen(
    viewState: CoinListState,
    onViewEvent: (CoinListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        CoinTrackerSearchBar(
            placeholder = stringResource(R.string.search),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(10.dp),
            onTextChange = {
                onViewEvent(CoinListEvent.OnSearchUser(it))
            }
        )
        CoinTrackerLoadingContent(
            isLoading = viewState.isLoading,
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items = viewState.filteredCoins.ifEmpty { viewState.coins }) {
                    CoinTrackerCardItem(
                        dto = it,
                        detailClick = { onViewEvent(CoinListEvent.OnDetailClick(it.id.orEmpty())) })
                }
            }
        }
    }
}