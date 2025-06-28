package com.devrachit.groww.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watchlists")
data class WatchlistEntity(
    @PrimaryKey(autoGenerate = true) val watchlist_id: Int = 0,
    val name: String,
    val count: Int
)