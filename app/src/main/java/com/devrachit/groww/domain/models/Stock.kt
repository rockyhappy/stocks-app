package com.devrachit.groww.domain.models

data class Stock(
    val changeAmount: String,
    val changePercentage: String,
    val price: String,
    val ticker: String,
    val volume: String
)

