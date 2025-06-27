package com.devrachit.groww.presentation.screens.details

data class DetailsScreenUiState(
    val title: String = "Details Screen",
    val ticker : String = "",
    val error : String? = null,
    val isLoading : Boolean = false
)