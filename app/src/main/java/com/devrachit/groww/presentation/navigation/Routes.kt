package com.devrachit.groww.presentation.navigation

import com.devrachit.groww.utility.constants.Constants.Companion.BOTTOM_BAR_ROUTE
import com.devrachit.groww.utility.constants.Constants.Companion.DETAIL_ROUTE
import com.devrachit.groww.utility.constants.Constants.Companion.DISPLAY_ROUTE
import com.devrachit.groww.utility.constants.Constants.Companion.HOME_ROUTE
import com.devrachit.groww.utility.constants.Constants.Companion.SEARCH_ROUTE
import com.devrachit.groww.utility.constants.Constants.Companion.WAITLIST_ROUTE

sealed class Screen(val route: String) {
    object HomeScreen : Screen(HOME_ROUTE)
    object DetailsScreen : Screen(DETAIL_ROUTE)
    object DisplayScreen : Screen(DISPLAY_ROUTE)
    object WaitlistScreen : Screen(WAITLIST_ROUTE)
    object BottomBarScreen : Screen(BOTTOM_BAR_ROUTE)
    object SearchScreen : Screen(SEARCH_ROUTE)
}