package com.devrachit.groww.data.remote.dto

import com.google.gson.annotations.SerializedName

data class StockDto(
    @SerializedName("change_amount")
    val changeAmount: String,
    @SerializedName("change_percentage")
    val changePercentage: String,
    val price: String,
    val ticker: String,
    val volume: String
)
