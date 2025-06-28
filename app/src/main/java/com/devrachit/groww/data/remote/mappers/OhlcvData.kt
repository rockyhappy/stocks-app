package com.devrachit.groww.data.remote.mappers

import com.devrachit.groww.data.remote.dto.DailyGraphDataDto
import com.devrachit.groww.data.remote.dto.IntraDayGraphDto
import com.devrachit.groww.data.remote.dto.MonthlyGraphDataDto
import com.devrachit.groww.data.remote.dto.OhlcvDataDto
import com.devrachit.groww.data.remote.dto.WeeklyGraphDataDto
import com.devrachit.groww.domain.models.OhlcvData

fun IntraDayGraphDto.toDomainModel() : Map<String, OhlcvData> {
    return this.timeSeries.mapValues { (_, value) ->
        OhlcvData(
            open = value.open,
            high = value.high,
            low = value.low,
            close = value.close,
            volume = value.volume
        )
    }

}
fun DailyGraphDataDto.toDomainModel() : Map<String, OhlcvData> {
    return this.timeSeries.mapValues { (_, value) ->
        OhlcvData(
            open = value.open,
            high = value.high,
            low = value.low,
            close = value.close,
            volume = value.volume
        )
    }

}
fun WeeklyGraphDataDto.toDomainModel() : Map<String, OhlcvData> {
    return this.timeSeries.mapValues { (_, value) ->
        OhlcvData(
            open = value.open,
            high = value.high,
            low = value.low,
            close = value.close,
            volume = value.volume
        )
    }

}
fun MonthlyGraphDataDto.toDomainModel() : Map<String, OhlcvData> {
    return this.timeSeries.mapValues { (_, value) ->
        OhlcvData(
            open = value.open,
            high = value.high,
            low = value.low,
            close = value.close,
            volume = value.volume
        )
    }

}
