package com.devrachit.groww.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MonthlyGraphDataDto(
    @SerializedName("Meta Data")
    val metaData: MetaData,

    @SerializedName("Monthly Time Series")
    val timeSeries: Map<String, OhlcvDataDto>
)
