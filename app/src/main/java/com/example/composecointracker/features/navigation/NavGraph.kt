package com.example.composecointracker.features.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composecointracker.features.component.CoinTrackerBottomAppBar
import com.example.composecointracker.features.component.CoinTrackerScaffold
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph() {
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDestination = navController
        .currentBackStackEntryAsState().value?.destination

    CoinTrackerScaffold(
        bottomBar = {
            BottomNav.values().forEach { navItem ->
                if (navItem.route == currentRoute) {
                    CoinTrackerBottomAppBar(
                        navController = navController,
                        currentDestination = currentDestination
                    )
                }
            }
        },
        backgroundColor = MaterialTheme.colors.background,
    ) { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = coinListNavigationRoute,
            Modifier.padding(innerPadding)
        ) {
            coinListScreen {
                navController.navigateToCoinDetail(it)
            }
            coinDetailScreen()
            coinFavoriteScreen()
        }
    }
}