package com.example.composebitcointracker.features.screen.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composebitcointracker.R
import com.example.composebitcointracker.features.component.BitcoinTrackerLoadingContent
import com.example.composebitcointracker.features.component.BitcoinTrackerSearchBar

@Composable
fun BitcoinListRoute(
    viewModel: BitcoinListViewModel = hiltViewModel(),
    navigateToDetail: () -> Unit
) {
    val viewState = viewModel.state.collectAsState().value

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                else -> {}
            }
        }
    }

    BitcoinListScreen(
        viewState,
        onViewEvent = viewModel::setEvent
    )
}

@Composable
private fun BitcoinListScreen(
    viewState: BitcoinListState,
    onViewEvent: (BitcoinListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        BitcoinTrackerSearchBar(
            placeholder = stringResource(R.string.search),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(10.dp),
            onTextChange = {
                onViewEvent(BitcoinListEvent.OnSearchUser(it))
            }
        )
        BitcoinTrackerLoadingContent(
            isLoading = viewState.isLoading,
            modifier = Modifier.fillMaxSize()
        ) {

        }
    }
}