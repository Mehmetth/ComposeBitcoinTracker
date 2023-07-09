package com.example.composebitcointracker.features.screen.favorite

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
fun BitcoinFavoriteRoute(
    viewModel: BitcoinFavoriteViewModel = hiltViewModel(),
) {
    val viewState = viewModel.state.collectAsState().value

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                else -> {}
            }
        }
    }

    BitcoinFavoriteScreen(
        viewState,
        onViewEvent = viewModel::setEvent
    )
}

@Composable
private fun BitcoinFavoriteScreen(
    viewState: BitcoinFavoriteState,
    onViewEvent: (BitcoinFavoriteEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(Color.Magenta))
}