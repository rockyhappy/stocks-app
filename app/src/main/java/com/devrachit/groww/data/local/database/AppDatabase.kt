package com.devrachit.groww.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devrachit.groww.data.local.dao.WatchlistDao
import com.devrachit.groww.data.local.entity.StocksEntity
import com.devrachit.groww.data.local.entity.WatchlistEntity
import com.devrachit.groww.data.local.entity.WatchlistStockCrossRefEntity

@Database(
    entities = [StocksEntity::class, WatchlistEntity::class, WatchlistStockCrossRefEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun watchlistDao(): WatchlistDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "groww_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}