package com.devrachit.groww.presentation.screens.displayList

import javax.inject.Inject
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class DisplayScreenViewmodel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow<DisplayListUiState>(DisplayListUiState())
    val uiState : StateFlow<DisplayListUiState> = _uiState.asStateFlow()
}