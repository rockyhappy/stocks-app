package com.devrachit.groww.presentation.screens.home

import com.devrachit.groww.domain.models.Stock

data class HomeScreenUiState(
    val title : String = "HomeScreen",
    val isLoading : Boolean = false,
    val error : String? = null,
    val gainersList : List<Stock> = emptyList(),
    val losersList : List<Stock> = emptyList(),
    val activeList : List<Stock> = emptyList(),
    val isRefreshing : Boolean = false
)
