package com.devrachit.groww.data.local.mappers

import com.devrachit.groww.data.local.entity.StocksEntity
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.devrachit.groww.data.local.entity.WatchlistEntity
import com.devrachit.groww.data.local.entity.WatchlistStockCrossRefEntity

data class StockWithWatchlist(
    @Embedded val stock: StocksEntity,
    @Relation(
        parentColumn = "ticker",
        entityColumn = "watchlist_id",
        associateBy = Junction(WatchlistStockCrossRefEntity::class)
    )
    val watchlist: List<WatchlistEntity>
)
