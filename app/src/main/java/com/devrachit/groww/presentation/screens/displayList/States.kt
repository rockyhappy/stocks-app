package com.devrachit.groww.presentation.screens.displayList

import com.devrachit.groww.domain.models.Stock

data class DisplayListUiState(
    val title: String = "Display Screen",
    val data: List<Stock> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error : String? = null,
)