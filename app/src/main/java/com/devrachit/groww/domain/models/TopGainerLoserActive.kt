package com.devrachit.groww.domain.models

data class TopGainersLosersActives(
    val topGainers: List<Stock>,
    val topLosers: List<Stock>,
    val mostActive: List<Stock>
)
