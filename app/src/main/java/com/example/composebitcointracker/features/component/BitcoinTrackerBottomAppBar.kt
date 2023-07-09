package com.example.composebitcointracker.features.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.os.trace
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navOptions
import com.example.composebitcointracker.features.navigation.BottomNav
import com.example.composebitcointracker.features.navigation.navigateBitcoinList
import com.example.composebitcointracker.features.navigation.navigateFavorite
import com.example.composebitcointracker.features.theme.BLUE
import com.example.composebitcointracker.features.theme.BOTTOM_BAR_SELECTED
import com.example.composebitcointracker.features.theme.BOTTOM_BAR_UNSELECTED

@Composable
fun BitcoinTrackerBottomAppBar(
    navController: NavController,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier,
        elevation = 10.dp,
        backgroundColor = BLUE
    ) {
        BottomNav.values().forEach { screen ->
            val selected = currentDestination.isBottomNavDestinationInHierarchy(screen)
            val primaryColor = BOTTOM_BAR_SELECTED
            val secondaryColor = BOTTOM_BAR_UNSELECTED

            BottomNavigationItem(
                alwaysShowLabel = true,
                selectedContentColor = primaryColor,
                unselectedContentColor = secondaryColor,
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = screen.iconId),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },

                label = {
                    Text(
                        text = stringResource(id = screen.titleTextId),
                        color = if (selected) primaryColor else secondaryColor,
                        style = MaterialTheme.typography.overline,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                },
                selected = selected,
                onClick = {
                    navigateToBottomNavDestination(screen, navController)
                }
            )
        }
    }
}

fun navigateToBottomNavDestination(bottomNav: BottomNav, navController: NavController) {
    trace("Navigation: ${bottomNav.name}") {
        val bottomNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (bottomNav) {
            BottomNav.BITCOIN -> navController.navigateBitcoinList(bottomNavOptions)
            BottomNav.FAVORITE -> navController.navigateFavorite(bottomNavOptions)
        }
    }
}

private fun NavDestination?.isBottomNavDestinationInHierarchy(destination: BottomNav) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false