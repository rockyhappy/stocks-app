package com.devrachit.groww.presentation.screens.displayList

import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrachit.groww.domain.models.Stock
import com.devrachit.groww.domain.usecases.GetGainerLoserActiveStocks.GetGainerLoserActiveStocks
import com.devrachit.groww.utility.networkUtility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DisplayScreenViewmodel @Inject constructor(
    private val getGainerLoserActiveStocks: GetGainerLoserActiveStocks
): ViewModel() {

    private val _uiState = MutableStateFlow<DisplayListUiState>(DisplayListUiState())
    val uiState : StateFlow<DisplayListUiState> = _uiState.asStateFlow()

    fun setListData(list: List<Stock>) {
        _uiState.update({ it.copy(data = list) })
    }
    fun getTopGainersLosersActiveDriver() {
        val coroutineScope = viewModelScope
        coroutineScope.launch(Dispatchers.IO) {
            getTopGainersLosers()
        }
    }

    suspend fun getTopGainersLosers() {
        getGainerLoserActiveStocks.invoke().collectLatest { response ->
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
                            gainersList = response.data?.topGainers ?: emptyList(),
                            losersList = response.data?.topLosers ?: emptyList(),
                            activeList = response.data?.mostActive ?: emptyList()
                        )
                    }
                }
            }
        }
    }
}