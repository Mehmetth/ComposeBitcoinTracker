package com.example.composebitcointracker.features.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.example.composebitcointracker.features.screen.favorite.BitcoinFavoriteRoute
import com.google.accompanist.navigation.animation.composable

const val favoriteNavigationRoute = "favorite_route"

fun NavController.navigateFavorite(
    navOptions: NavOptions? = null
) {
    this.navigate(favoriteNavigationRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.favoriteScreen() {
    composable(route = favoriteNavigationRoute) {
        BitcoinFavoriteRoute()
    }
}