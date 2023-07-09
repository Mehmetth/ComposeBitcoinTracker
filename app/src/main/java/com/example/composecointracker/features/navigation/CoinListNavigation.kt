package com.example.composecointracker.features.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.example.composecointracker.features.screen.list.CoinListRoute
import com.google.accompanist.navigation.animation.composable

const val coinListNavigationRoute = "coin_route"

fun NavController.navigateCoinList(
    navOptions: NavOptions? = null
) {
    this.navigate(coinListNavigationRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.coinListScreen(
    navigateToDetail: (String) -> Unit
) {
    composable(route = coinListNavigationRoute) {
        CoinListRoute(
            navigateToDetail = navigateToDetail
        )
    }
}