package com.devrachit.groww.data.local.entity

import androidx.room.Entity

@Entity(tableName = "watchlist_stock_cross_ref",primaryKeys = ["watchlist_id", "ticker"])
data class WatchlistStockCrossRefEntity(
    val watchlist_id: Int,
    val ticker: String
)
