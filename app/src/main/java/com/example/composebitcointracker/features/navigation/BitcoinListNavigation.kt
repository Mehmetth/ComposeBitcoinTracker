package com.example.composebitcointracker.features.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.example.composebitcointracker.features.screen.list.BitcoinListRoute
import com.google.accompanist.navigation.animation.composable

const val bitcoinListNavigationRoute = "bitcoin_route"

fun NavController.navigateBitcoinList(
    navOptions: NavOptions? = null
) {
    this.navigate(bitcoinListNavigationRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.bitcoinListScreen() {
    composable(route = bitcoinListNavigationRoute) {
        BitcoinListRoute(
            navigateToDetail = { /*TODO*/ }
        )
    }
}