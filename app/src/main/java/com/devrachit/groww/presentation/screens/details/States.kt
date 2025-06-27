package com.devrachit.groww.presentation.screens.details

import com.devrachit.groww.domain.models.CompanyDetails

data class DetailsScreenUiState(
    val title: String = "Details Screen",
    val ticker : String = "",
    val error : String? = null,
    val isLoading : Boolean = false,
    val companyDetails : CompanyDetails?= null,
    val isRefreshing: Boolean = false,
)