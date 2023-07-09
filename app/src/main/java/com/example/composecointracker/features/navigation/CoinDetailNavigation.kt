package com.example.composecointracker.features.navigation

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.composecointracker.features.screen.detail.CoinDetailRoute
import com.google.accompanist.navigation.animation.composable

const val coinDetailNavigationRoute = "coin_detail"
const val coinIdArg = "coin_id"

internal class CoinDetailArgs(val coinId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(coinId = checkNotNull(Uri.decode(savedStateHandle[coinIdArg])))
}

fun NavController.navigateToCoinDetail(coinId: String, navOptions: NavOptions? = null) {
    val id = Uri.encode(coinId)
    this.navigate(coinDetailNavigationRoute.plus("/$id"), navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.coinDetailScreen() {
    composable(route = coinDetailNavigationRoute,
        arguments = listOf(
            navArgument(coinIdArg) { type = NavType.StringType }
        )) {
        CoinDetailRoute()
    }
}