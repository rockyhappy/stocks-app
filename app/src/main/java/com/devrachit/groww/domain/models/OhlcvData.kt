package com.devrachit.groww.domain.models

import com.google.gson.annotations.SerializedName

data class OhlcvData(
    val open: String,
    val high: String,
    val low: String,
    val close: String,
    val volume: String
)