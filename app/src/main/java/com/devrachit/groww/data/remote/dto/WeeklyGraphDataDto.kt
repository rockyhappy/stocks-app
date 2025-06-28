package com.devrachit.groww.data.remote.dto

import com.google.gson.annotations.SerializedName


data class WeeklyGraphDataDto(
    @SerializedName("Meta Data")
    val metaData: MetaData,

    @SerializedName("Weekly Time Series")
    val timeSeries: Map<String, OhlcvDataDto>
)
