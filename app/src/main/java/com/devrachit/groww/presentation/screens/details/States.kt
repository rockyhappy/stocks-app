package com.devrachit.groww.presentation.screens.details

import com.devrachit.groww.data.local.entity.WatchlistEntity
import com.devrachit.groww.data.remote.dto.OhlcvDataDto
import com.devrachit.groww.domain.models.CompanyDetails
import com.devrachit.groww.domain.models.OhlcvData
import com.devrachit.groww.domain.models.Stock

data class DetailsScreenUiState(
    val title: String = "Details Screen",
    val ticker : String = "",
    val error : String? = null,
    val isLoading : Boolean = false,
    val companyDetails : CompanyDetails?= null,
    val isRefreshing: Boolean = false,
    val stock:Stock?= null,
    val isBookmarkAdded : Boolean = false,
    val watchlistEntry :String? = null,
    val watchlistError :String? = null,
    val isWatchlistError :Boolean = false,
    val stockWatchlist: List<WatchlistEntity> = emptyList(),
    val allWatchlist: List<WatchlistEntity> = emptyList(),
)

data class GraphState(
    val data : Map<String, OhlcvData> = emptyMap(),
    val graphType: GraphType = GraphType.INTRA_DAY,
    val isLoading: Boolean = false,
    val error: String? = null
)

enum class GraphType {
    INTRA_DAY,
    DAILY,
    WEEKLY,
    MONTHLY
}