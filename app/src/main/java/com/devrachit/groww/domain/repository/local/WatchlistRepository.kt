package com.devrachit.groww.domain.repository.local

import com.devrachit.groww.data.local.entity.StocksEntity
import com.devrachit.groww.data.local.entity.WatchlistEntity

interface WatchlistRepository {
    suspend fun getWatchlist(): List<WatchlistEntity>
    suspend fun addWatchList(watchlistEntity: WatchlistEntity): Long
    suspend fun deleteWatchList(watchlistEntity: WatchlistEntity)
    suspend fun isStockInWatchlist(symbol: String): List<WatchlistEntity>
    suspend fun addStockToWatchlist(stock: StocksEntity, watchlistEntity: WatchlistEntity)
    suspend fun deleteStockFromWatchlist(stock: StocksEntity, watchlistEntity: WatchlistEntity)
    suspend fun getStocksFromWatchlist(watchlistId: Int): List<StocksEntity>
    suspend fun updateWatchlistCount(watchlistId: Int)
    suspend fun getWatchlistStockCount(watchlistId: Int): Int
    suspend fun updateAllWatchlistCounts()
}
