package com.devrachit.groww.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrachit.groww.data.local.entity.SearchHistoryEntity
import com.devrachit.groww.domain.models.FilterType
import com.devrachit.groww.domain.usecases.SearchUsecases.AddToSearchHistory
import com.devrachit.groww.domain.usecases.SearchUsecases.GetSearchHistory
import com.devrachit.groww.domain.usecases.SearchUsecases.GetSearchResults
import com.devrachit.groww.utility.networkUtility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewmodel @Inject constructor(
    private val getSearchResults: GetSearchResults,
    private val getSearchHistory: GetSearchHistory,
    private val addToSearchHistory: AddToSearchHistory
): ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _searchStates = MutableStateFlow(SearchScreenUIState())
    val searchStates: StateFlow<SearchScreenUIState> = _searchStates.asStateFlow()

    private val _filterType = MutableStateFlow(FilterType.None)
    val filterType = _filterType.asStateFlow()

    init {
        handleSearchQuery()
        getSearchHistoryData()
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    @OptIn(FlowPreview::class)
    private fun handleSearchQuery() {
        _searchText
            .debounce(500L)
            .filter { it.isNotBlank() }
            .onEach { query ->
                _searchStates.update { it.copy(isLoading = true) }
                getSearchResults(query).onEach { response ->
                    when (response) {
                        is Resource.Success -> {
                            _searchStates.update {
                                it.copy(
                                    isLoading = false,
                                    searchResults = response.data ?: emptyList(),
                                    error = null
                                )
                            }
                        }

                        is Resource.Error -> {
                            _searchStates.update {
                                it.copy(
                                    isLoading = false,
                                    error = response.message
                                )
                            }
                        }

                        is Resource.Loading -> {
                            _searchStates.update { it.copy(isLoading = true) }
                        }
                    }
                }.launchIn(viewModelScope)
            }.launchIn(viewModelScope)
    }

    fun resetErrorMessage() {
        _searchStates.update { it.copy(error = null) }
    }

    fun clearSearchText() {
        _searchText.value = ""
        _searchStates.update {
            it.copy(searchResults = emptyList())
        }
    }

    private fun getSearchHistoryData() {
        getSearchHistory().onEach { history ->
            _searchStates.update { it.copy(searchHistory = history.reversed()) }
        }.launchIn(viewModelScope)
    }

    fun addToSearchHistoryData() {
        if (searchText.value.isNotBlank()) {
            viewModelScope.launch {
                addToSearchHistory(
                    SearchHistoryEntity(
                        query = searchText.value,
                        timestamp = System.currentTimeMillis()
                    )
                )
            }
        }
    }

    fun updateFilter(type: FilterType) {
        _filterType.value = type
    }
}
