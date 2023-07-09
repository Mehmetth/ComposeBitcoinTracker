package com.example.composecointracker.features.screen.favorite

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
fun CoinFavoriteRoute(
    viewModel: CoinFavoriteViewModel = hiltViewModel(),
) {
    val viewState = viewModel.state.collectAsState().value

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                else -> {}
            }
        }
    }

    CoinFavoriteScreen(
        viewState,
        onViewEvent = viewModel::setEvent
    )
}

@Composable
private fun CoinFavoriteScreen(
    viewState: CoinFavoriteState,
    onViewEvent: (CoinFavoriteEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta)
    )
}