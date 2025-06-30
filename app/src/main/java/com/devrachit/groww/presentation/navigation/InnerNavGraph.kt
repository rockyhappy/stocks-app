package com.devrachit.groww.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.devrachit.groww.domain.models.DisplayPassData
import com.devrachit.groww.domain.models.Stock
import com.devrachit.groww.domain.models.StockType
import com.devrachit.groww.presentation.screens.home.HomeScreen
import com.devrachit.groww.presentation.screens.home.HomeScreenViewmodel
import com.devrachit.groww.presentation.screens.search.SearchScreen
import com.devrachit.groww.presentation.screens.search.SearchViewmodel
import com.devrachit.groww.presentation.screens.watchlist.WatchlistScreen
import com.devrachit.groww.presentation.screens.watchlist.WatchlistScreenViewmodel
import com.devrachit.groww.utility.constants.Constants.Companion.MOSTLY_TRADED
import com.devrachit.groww.utility.constants.Constants.Companion.START_DESTINATION_INNER_NAV
import com.devrachit.groww.utility.constants.Constants.Companion.TOP_GAINERS
import com.devrachit.groww.utility.constants.Constants.Companion.TOP_LOSERS

@Composable
fun NavGraph(
    modifier : Modifier=Modifier,
    navController: NavHostController = rememberNavController(),
    onNavigateToDetail: (stock:Stock) -> Unit,
    onNavigateToDisplay: (passData: DisplayPassData) -> Unit,
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
                onNavigationToDisplay ={ type: StockType->
                    val passData = DisplayPassData(
                        title = when(type){
                            is StockType.Gainer -> TOP_GAINERS
                            is StockType.Loser -> TOP_LOSERS
                            is StockType.Active -> MOSTLY_TRADED
                        },
                        list = uiState.gainersList
                    )
                    onNavigateToDisplay(passData)
                },
                onRefresh = viewModel::getTopGainersLosersActiveDriver
            )
        }
        animatedComposable(Screen.WaitlistScreen.route)
        {
            val viewmodel = hiltViewModel<WatchlistScreenViewmodel>()
            val uiState = viewmodel.uiState.collectAsStateWithLifecycle().value
            WatchlistScreen(
                uiState=uiState,
                title = uiState.title,
                onNavigationToDisplay = onNavigateToDisplay,
                onNavigateToDetail = onNavigateToDetail,
                onRefresh = viewmodel::onRefresh,
                onWatchlistEntry = viewmodel::onWatchlistEntryChanged,
                addWatchlist = viewmodel::addWatchlist,
                deleteWatchlist = viewmodel::deleteWatchlist,
                onCardClick = { watchlistEntity ->
                    viewmodel.getStocksFromWatchlist(watchlistEntity, onNavigateToDisplay)
                }
            )
        }
        animatedComposable(Screen.SearchScreen.route)
        {
            val viewmodel = hiltViewModel<SearchViewmodel>()
            val uiState = viewmodel.searchStates.collectAsStateWithLifecycle().value
            val searchText = viewmodel.searchText.collectAsStateWithLifecycle().value
            val filterType = viewmodel.filterType.collectAsStateWithLifecycle().value
            SearchScreen(
                searchText = searchText,
                uiState = uiState,
                filterType = filterType,
                onSearchTextChange = viewmodel::onSearchTextChange,
                onClearSearchText = viewmodel::clearSearchText,
                onAddToSearchHistory = viewmodel::addToSearchHistoryData,
                onUpdateFilter = viewmodel::updateFilter,
                onResetErrorMessage = viewmodel::resetErrorMessage,
                navigateTo = { symbol ->
                    // Navigate to details screen with the symbol
                    // You might want to convert symbol to Stock object if needed
                },
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
