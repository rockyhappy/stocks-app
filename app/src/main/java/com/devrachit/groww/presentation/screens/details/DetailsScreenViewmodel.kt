package com.devrachit.groww.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrachit.groww.domain.usecases.CompanyStocksDetails.GetCompanyDetails
import com.devrachit.groww.domain.usecases.CompanyStocksDetails.GetIntraDayGraphData
import com.devrachit.groww.utility.networkUtility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewmodel @Inject constructor(
    private val GetCompanyDetailsUsecase : GetCompanyDetails,
    private val getIntraDayOhlcvData: GetIntraDayGraphData
) : ViewModel() {


    private val _uiState = MutableStateFlow(DetailsScreenUiState())
    val uiState : StateFlow<DetailsScreenUiState> = _uiState.asStateFlow()

    private val _graphState = MutableStateFlow(GraphState())
    val graphState : StateFlow<GraphState> = _graphState.asStateFlow()

    init{
//        getCompanyDetails()
    }

    fun setTicker(ticker: String) {
        _uiState.update { it.copy(ticker = ticker) }

    }
    fun getCompanyDetails() {
        val coroutineScope = viewModelScope
        coroutineScope.launch(Dispatchers.IO) {
            getCompanyInfo(ticker = _uiState.value.ticker)
        }
        coroutineScope.launch(Dispatchers.IO) {
            getIntraDayGraphData(ticker = _uiState.value.ticker)
        }
    }
    private suspend fun getCompanyInfo(ticker: String) {
        GetCompanyDetailsUsecase.invoke(ticker).collectLatest { response ->
            when (response) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(isLoading = false, error = response.message)
                    }
                }

                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            companyDetails = response.data,
                            error = null
                        )
                    }
                }
            }
        }
    }
    private suspend fun getIntraDayGraphData(ticker: String) {
        getIntraDayOhlcvData.invoke(ticker).collectLatest { response ->
            when (response) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(isLoading = false, error = response.message)
                    }
                }

                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = null
                        )
                    }
                    _graphState.update {
                        it.copy(
                            data = response.data ?: emptyMap(),
                        )
                    }

                }
            }
        }
    }
}