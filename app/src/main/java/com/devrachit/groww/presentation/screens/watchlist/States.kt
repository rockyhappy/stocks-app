package com.devrachit.groww.presentation.screens.watchlist

import com.devrachit.groww.data.local.entity.WatchlistEntity
import com.devrachit.groww.domain.models.Stock

data class WatchlistScreenUiStates(
    val title: String = "Watchlist",
    val isLoading: Boolean = false,
    val error: String? = null,
    val allWatchlist: List<WatchlistEntity> = emptyList(),
    val watchlistEntry :String? = null,
    val watchlistError :String? = null,
    val isWatchlistError :Boolean = false,
    val stocksListPass: List<Stock> = emptyList(),
)