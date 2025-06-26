package com.devrachit.groww.data.remote.dto

import com.devrachit.groww.domain.models.Stock

data class TopGainersLosersActivesDto(
    val last_updated: String,
    val metadata: String,
    val most_actively_traded: List<StockDto>,
    val top_gainers: List<StockDto>,
    val top_losers: List<StockDto>
)