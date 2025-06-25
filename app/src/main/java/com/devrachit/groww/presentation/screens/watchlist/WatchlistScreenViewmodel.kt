package com.devrachit.groww.presentation.screens.watchlist


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WatchlistScreenViewmodel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(WatchlistScreenUiStates())
    val uiState: StateFlow<WatchlistScreenUiStates> = _uiState.asStateFlow()
}