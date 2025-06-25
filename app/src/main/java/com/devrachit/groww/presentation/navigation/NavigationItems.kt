package com.devrachit.groww.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.devrachit.groww.R


data class NavItemData(
    val label: String,
    @DrawableRes val outlinedIcon: Int,
    @DrawableRes val filledIcon: Int,
    val route: String
)


@Composable
fun rememberNavigationItems() = remember {
    mapOf(
        0 to NavItemData(
            "Home",
            R.drawable.ic_home_outlined,
            R.drawable.ic_home_filled,
            Screen.HomeScreen.route
        ),

        1 to NavItemData(
            "Watchlist",
            R.drawable.ic_sheets_outlined,
            R.drawable.ic_sheets_filled,
            Screen.WaitlistScreen.route
        )
    )
}

/**
 * Get the index of a route in the navigation items
 */
fun getRouteIndex(route: String?): Int? {
    if (route == null) return null

    return when (route) {
        Screen.HomeScreen.route -> 0
        Screen.WaitlistScreen.route -> 1
        else -> null
    }
}