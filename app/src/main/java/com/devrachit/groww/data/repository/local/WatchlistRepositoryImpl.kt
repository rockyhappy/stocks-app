package com.devrachit.groww.data.repository.local

import com.devrachit.groww.data.local.dao.WatchlistDao
import com.devrachit.groww.data.local.entity.StocksEntity
import com.devrachit.groww.data.local.entity.WatchlistEntity
import com.devrachit.groww.data.local.entity.WatchlistStockCrossRefEntity
import com.devrachit.groww.domain.repository.local.WatchlistRepository
import javax.inject.Inject

class WatchlistRepositoryImpl @Inject constructor(
    private val watchlistDao: WatchlistDao
) : WatchlistRepository {

    override suspend fun getWatchlist(): List<WatchlistEntity> {
        return watchlistDao.getAllWatchlists()
    }

    override suspend fun addWatchList(watchlistEntity: WatchlistEntity): Long {
        return watchlistDao.insertWatchlist(watchlistEntity)
    }

    override suspend fun deleteWatchList(watchlistEntity: WatchlistEntity) {
        return watchlistDao.deleteWatchlist(watchlistEntity)
    }

    override suspend fun isStockInWatchlist(symbol: String): List<WatchlistEntity> {
        return watchlistDao.getStockWithWatchlists(symbol).watchlist
    }

    override suspend fun addStockToWatchlist(
        stock: StocksEntity,
        watchlistEntity: WatchlistEntity
    ) {

        watchlistDao.insertStock(stock = stock)
        val crossRef = WatchlistStockCrossRefEntity(
            watchlist_id = watchlistEntity.watchlist_id,
            ticker = stock.ticker
        )
        watchlistDao.insertCrossRef(crossRef = crossRef)
        updateWatchlistCount(watchlistEntity.watchlist_id)
    }

    override suspend fun deleteStockFromWatchlist(
        stock: StocksEntity,
        watchlistEntity: WatchlistEntity
    ) {
        val crossRef = WatchlistStockCrossRefEntity(
            watchlist_id = watchlistEntity.watchlist_id,
            ticker = stock.ticker
        )
        watchlistDao.deleteCrossRef(crossRef)
        updateWatchlistCount(watchlistEntity.watchlist_id)
//        val stockWithWatchlists = watchlistDao.getStockWithWatchlists(stock.ticker)
//        if (stockWithWatchlists.watchlist.isEmpty()) {
//            watchlistDao.deleteStock(stock)
//        }
    }

    override suspend fun getStocksFromWatchlist(watchlistId: Int): List<StocksEntity> {
        return watchlistDao.getWatchlistWithStocks(watchlistId = watchlistId).stocks
    }

    override suspend fun updateWatchlistCount(watchlistId: Int) {
        val count = watchlistDao.getWatchlistStockCount(watchlistId)
        watchlistDao.updateWatchlistCount(watchlistId, count)
    }

    override suspend fun getWatchlistStockCount(watchlistId: Int): Int {
        return watchlistDao.getWatchlistStockCount(watchlistId)
    }

    override suspend fun updateAllWatchlistCounts() {
        watchlistDao.updateAllWatchlistCounts()
    }
}
