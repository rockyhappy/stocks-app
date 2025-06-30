package com.devrachit.groww.data.local.mappers

import com.devrachit.groww.data.local.entity.StocksEntity
import com.devrachit.groww.domain.models.Stock

fun StocksEntity.toDomainModel(): Stock{
    return Stock(
        changeAmount=changeAmount,
        changePercentage=changePercentage,
        price=price,
        ticker=ticker,
        volume=volume
    )

}