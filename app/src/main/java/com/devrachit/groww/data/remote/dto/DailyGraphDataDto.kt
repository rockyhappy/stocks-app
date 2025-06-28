package com.devrachit.groww.data.remote.dto

import com.google.gson.annotations.SerializedName


data class DailyGraphDataDto(
    @SerializedName("Meta Data")
    val metaData: MetaData,

    @SerializedName("Time Series (Daily)")
    val timeSeries: Map<String, OhlcvDataDto>
)