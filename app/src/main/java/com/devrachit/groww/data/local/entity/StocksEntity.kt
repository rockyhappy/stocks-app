package com.devrachit.groww.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "stocks")
data class StocksEntity(
    @PrimaryKey val ticker: String,
    val changeAmount: String,
    val changePercentage: String,
    val price: String,
    val volume: String
) : Parcelable