package com.devrachit.groww.presentation.screens.bottomBar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BottomBarScreenViewmodel @Inject constructor(): ViewModel()  {
    private val _uiStates = MutableStateFlow<BottomBarScreenUiState>(BottomBarScreenUiState())
    val uiState: StateFlow<BottomBarScreenUiState> = _uiStates.asStateFlow()

}