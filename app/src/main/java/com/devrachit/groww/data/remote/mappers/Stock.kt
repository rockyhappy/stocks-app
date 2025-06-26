package com.devrachit.groww.data.remote.mappers

import com.devrachit.groww.data.remote.dto.StockDto
import com.devrachit.groww.domain.models.Stock

fun StockDto.toDomainModel(): Stock{
    return Stock(
        changeAmount=this.changeAmount,
        changePercentage=this.changePercentage,
        price=this.price,
        ticker=this.ticker,
        volume=this.volume
    )
}