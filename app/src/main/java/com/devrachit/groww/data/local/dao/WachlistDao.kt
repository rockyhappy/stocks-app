package com.devrachit.groww.data.local.dao

import androidx.room.*
import com.devrachit.groww.data.local.entity.StocksEntity
import com.devrachit.groww.data.local.entity.WatchlistEntity
import com.devrachit.groww.data.local.entity.WatchlistStockCrossRefEntity
import com.devrachit.groww.data.local.mappers.StockWithWatchlist
import com.devrachit.groww.data.local.mappers.WatchlistWithStocks

@Dao
interface WatchlistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchlist(watchlist: WatchlistEntity):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStock(stock: StocksEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRef(crossRef: WatchlistStockCrossRefEntity)


    @Delete
    suspend fun deleteWatchlist(watchlist: WatchlistEntity)

    @Delete
    suspend fun deleteStock(stock: StocksEntity)

    @Delete
    suspend fun deleteCrossRef(crossRef: WatchlistStockCrossRefEntity)

    @Query("DELETE FROM watchlists WHERE watchlist_id = :watchlistId")
    suspend fun deleteWatchlistById(watchlistId: Int)

    @Query("DELETE FROM stocks WHERE ticker = :ticker")
    suspend fun deleteStockByTicker(ticker: String)

    @Query("DELETE FROM watchlist_stock_cross_ref WHERE watchlist_id = :watchlistId")
    suspend fun deleteAllStocksFromWatchlist(watchlistId: Int)

    @Query("UPDATE watchlists SET count = :count WHERE watchlist_id = :watchlistId")
    suspend fun updateWatchlistCount(watchlistId: Int, count: Int)

    @Query("SELECT COUNT(*) FROM watchlist_stock_cross_ref WHERE watchlist_id = :watchlistId")
    suspend fun getWatchlistStockCount(watchlistId: Int): Int

    @Query("""
        UPDATE watchlists 
        SET count = (
            SELECT COUNT(*) 
            FROM watchlist_stock_cross_ref 
            WHERE watchlist_stock_cross_ref.watchlist_id = watchlists.watchlist_id
        )
    """)
    suspend fun updateAllWatchlistCounts()

    @Query("SELECT * FROM watchlists")
    suspend fun getAllWatchlists(): List<WatchlistEntity>

    @Transaction
    @Query("SELECT * FROM watchlists WHERE watchlist_id = :watchlistId")
    suspend fun getWatchlistWithStocks(watchlistId: Int): WatchlistWithStocks

    @Transaction
    @Query("SELECT * FROM stocks WHERE ticker = :ticker")
    suspend fun getStockWithWatchlists(ticker: String): StockWithWatchlist
}
