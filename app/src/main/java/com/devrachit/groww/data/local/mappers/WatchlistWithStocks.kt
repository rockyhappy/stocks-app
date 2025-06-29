package com.devrachit.groww.data.local.mappers

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.devrachit.groww.data.local.entity.StocksEntity
import com.devrachit.groww.data.local.entity.WatchlistEntity
import com.devrachit.groww.data.local.entity.WatchlistStockCrossRefEntity

data class WatchlistWithStocks(
    @Embedded val watchlist: WatchlistEntity,
    @Relation(
        parentColumn = "watchlist_id",
        entityColumn = "ticker",
        associateBy = Junction(WatchlistStockCrossRefEntity::class)
    )
    val stocks: List<StocksEntity>
)
