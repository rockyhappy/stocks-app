package com.devrachit.groww.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.devrachit.groww.presentation.screens.home.HomeScreen
import com.devrachit.groww.presentation.screens.home.HomeScreenViewmodel
import com.devrachit.groww.presentation.screens.watchlist.WatchlistScreen
import com.devrachit.groww.presentation.screens.watchlist.WatchlistScreenViewmodel
import com.devrachit.groww.utility.constants.Constants.Companion.START_DESTINATION_INNER_NAV

@Composable
fun NavGraph(
    modifier : Modifier=Modifier,
    navController: NavHostController = rememberNavController(),
    onNavigateToDetail: () -> Unit,
    onNavigateToDisplay: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = START_DESTINATION_INNER_NAV,
        modifier = modifier
    ) {
        animatedComposable(Screen.HomeScreen.route)
        {
            val viewModel = hiltViewModel<HomeScreenViewmodel>()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
            HomeScreen(
                title = uiState.title,
                uiState = uiState,
                onNavigateToDetail = onNavigateToDetail,
                onNavigationToDisplay = onNavigateToDisplay,
                onRefresh = viewModel::getTopGainersLosersActiveDriver
            )
        }
        animatedComposable(Screen.WaitlistScreen.route)
        {
            val viewmodel = hiltViewModel<WatchlistScreenViewmodel>()
            val uiState = viewmodel.uiState.collectAsStateWithLifecycle().value
            WatchlistScreen(
                title = uiState.title,
                onNavigationToDisplay = onNavigateToDisplay,
                onNavigateToDetail = onNavigateToDetail
            )
        }
    }
}