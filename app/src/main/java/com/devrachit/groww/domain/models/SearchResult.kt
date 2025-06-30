package com.devrachit.groww.domain.models

data class SearchResult(
    val symbol: String,
    val name: String,
    val type: FilterType,
    val region: String,
    val marketOpen: String,
    val marketClose: String,
    val timezone: String,
    val currency: String,
    val matchScore: String
)

enum class FilterType(val text: String) {
    Equity("Equity"),
    ETF("ETF"),
    MutualFund("Mutual Fund"),
    None("All")
}