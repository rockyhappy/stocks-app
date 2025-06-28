package com.devrachit.groww.presentation.screens.watchlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.devrachit.groww.R
import com.devrachit.groww.data.local.entity.WatchlistEntity
import com.devrachit.groww.domain.models.Stock
import com.devrachit.groww.presentation.screens.watchlist.components.EmptyWatchlistState
import com.devrachit.groww.presentation.screens.watchlist.components.WatchlistEntryForm
import com.devrachit.groww.presentation.screens.watchlist.components.WatchlistItemCard
import com.devrachit.groww.presentation.screens.watchlist.components.WatchlistTitle

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun WatchlistScreen(
    uiState: WatchlistScreenUiStates,
    title: String,
    onNavigateToDetail: (stock: Stock) -> Unit,
    onNavigationToDisplay: () -> Unit,
    onRefresh: () -> Unit,
    onWatchlistEntry: (watchlistEntry: String) -> Unit,
    addWatchlist: (name: String) -> Unit,
    deleteWatchlist: (watchlistEntity: WatchlistEntity) -> Unit
) {
    LaunchedEffect(Unit) {
        onRefresh()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    )
    {
        val pullRefreshState = rememberPullRefreshState(
            refreshing = uiState.isLoading,
            onRefresh = { onRefresh() }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
                .background(color = colorResource(R.color.white)),
        )
        {
            item {
                WatchlistTitle(title = title)
                WatchlistEntryForm(
                    watchlistEntry = uiState.watchlistEntry,
                    isWatchlistError = uiState.isWatchlistError,
                    onWatchlistEntry = onWatchlistEntry,
                    addWatchlist = addWatchlist
                )
            }

            if (uiState.allWatchlist.isEmpty() && uiState.isLoading.not()) {
                item {
                    EmptyWatchlistState()
                }
            }

            items(uiState.allWatchlist.size) { index ->
                WatchlistItemCard(
                    watchlistEntity = uiState.allWatchlist[index],
                    deleteWatchlist = deleteWatchlist,
                    onWatchlistEntry = onWatchlistEntry
                )
            }
        }

        PullRefreshIndicator(
            refreshing = uiState.isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            backgroundColor = colorResource(id = R.color.white),
            contentColor = colorResource(id = R.color.black)
        )
    }
}
