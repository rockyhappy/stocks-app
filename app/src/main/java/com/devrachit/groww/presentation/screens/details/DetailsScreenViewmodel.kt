package com.devrachit.groww.presentation.screens.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewmodel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(DetailsScreenUiState())
    val uiState : StateFlow<DetailsScreenUiState> = _uiState.asStateFlow()
}