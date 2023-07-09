package com.example.composecointracker.features.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.composecointracker.R

enum class BottomNav(
    val route: String,
    @DrawableRes val iconId: Int,
    @StringRes val titleTextId: Int
) {
    COIN(
        coinListNavigationRoute,
        R.drawable.ic_coin_list,
        R.string.coin_list,
    ),
    FAVORITE(
        favoriteNavigationRoute,
        R.drawable.ic_favorite,
        R.string.favorite
    )
}