package com.devrachit.groww.presentation.screens.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.devrachit.groww.data.local.entity.WatchlistEntity
import com.devrachit.groww.presentation.screens.details.DetailsScreenUiState
import com.devrachit.groww.presentation.screens.watchlist.components.EmptyWatchlistState
import com.devrachit.groww.presentation.screens.watchlist.components.WatchlistEntryForm
import com.devrachit.groww.presentation.screens.watchlist.components.WatchlistItemCard
import com.devrachit.groww.presentation.screens.watchlist.components.WatchlistTitle

@Composable
fun BottomSheetContent(
    modifier: Modifier = Modifier,
    uiState: DetailsScreenUiState,
    onWatchlistEntryChanged : (String) -> Unit,
    addWatchlist: (String) -> Unit,
    deleteWatchlist: (watchlistEntity: WatchlistEntity) -> Unit,
    addStockToWatchlist: (watchlistEntity: WatchlistEntity) -> Unit,
    deleteStockFromWatchlist: (watchlistEntity: WatchlistEntity) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            WatchlistTitle(title = "Add to Watchlist")
            WatchlistEntryForm(
                watchlistEntry = uiState.watchlistEntry,
                isWatchlistError = uiState.isWatchlistError,
                onWatchlistEntry = onWatchlistEntryChanged,
                addWatchlist = addWatchlist
            )
        }
        if (uiState.allWatchlist.isEmpty() && uiState.isLoading.not()) {
            item {
                EmptyWatchlistState()
            }
        }
        items(uiState.allWatchlist.size) { index ->
            CheckWatchlistItemCard(
                watchlistEntity = uiState.allWatchlist[index],
                deleteWatchlist = deleteWatchlist,
                onWatchlistEntry = {},
                onCheckedChange = { isChecked ->
                    if (isChecked) {
                        addStockToWatchlist(uiState.allWatchlist[index])
                    }
                    else {
                        deleteStockFromWatchlist(uiState.allWatchlist[index])
                    }
                },
                isChecked = if(uiState.stockWatchlist.contains(uiState.allWatchlist[index])) true else false
            )
        }
    }
}