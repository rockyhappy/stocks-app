package com.devrachit.groww.data.local.mappers

import com.devrachit.groww.data.remote.dto.BestMatchesDto
import com.devrachit.groww.domain.models.FilterType
import com.devrachit.groww.domain.models.SearchResult

fun BestMatchesDto.toDomainModel() = SearchResult(
    symbol, name, convertToType(type),region,marketOpen, marketClose,timezone,currency,matchScore
)
fun convertToType(str: String): FilterType {
    FilterType.entries.forEach {
        if (str == it.name) return it
    }
    return FilterType.None
}
