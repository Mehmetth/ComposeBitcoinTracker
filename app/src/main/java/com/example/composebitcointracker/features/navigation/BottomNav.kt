package com.example.composebitcointracker.features.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.composebitcointracker.R

enum class BottomNav(
    val route: String,
    @DrawableRes val iconId: Int,
    @StringRes val titleTextId: Int
) {
    BITCOIN(
        bitcoinListNavigationRoute,
        R.drawable.ic_bitcoin_list,
        R.string.bitcoin_list,
    ),
    FAVORITE(
        favoriteNavigationRoute,
        R.drawable.ic_favorite,
        R.string.favorite
    )
}