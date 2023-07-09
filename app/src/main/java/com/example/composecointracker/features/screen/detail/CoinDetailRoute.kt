package com.example.composecointracker.features.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CoinDetailRoute(
    viewModel: CoinDetailViewModel = hiltViewModel(),
) {
    val viewState = viewModel.state.collectAsState().value

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                else -> {}
            }
        }
    }

    CoinDetailScreen(
        viewState,
        onViewEvent = viewModel::setEvent
    )
}

@Composable
private fun CoinDetailScreen(
    viewState: CoinDetailState,
    onViewEvent: (CoinDetailEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red)
    )
}