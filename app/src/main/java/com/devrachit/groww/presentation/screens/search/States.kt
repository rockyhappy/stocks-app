package com.devrachit.groww.presentation.screens.search

import com.devrachit.groww.data.local.entity.SearchHistoryEntity
import com.devrachit.groww.domain.models.SearchResult

data class SearchScreenUIState(
    val isLoading: Boolean = false,
    val searchResults: List<SearchResult> = emptyList(),
    val searchHistory: List<SearchHistoryEntity> = emptyList(),
    val error: String? = null
)
