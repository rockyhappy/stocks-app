package com.devrachit.groww.data.remote.mappers

import com.devrachit.groww.data.remote.dto.TopGainersLosersActivesDto
import com.devrachit.groww.domain.models.TopGainersLosersActives

fun TopGainersLosersActivesDto.toDomainModel(): TopGainersLosersActives{
    return TopGainersLosersActives(
        topGainers = this.top_gainers.map { it.toDomainModel() },
        topLosers = this.top_losers.map { it.toDomainModel() },
        mostActive = this.most_actively_traded.map { it.toDomainModel() }
    )
}