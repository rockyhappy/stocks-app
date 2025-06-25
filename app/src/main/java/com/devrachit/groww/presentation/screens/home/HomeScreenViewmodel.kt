package com.devrachit.groww.presentation.screens.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewmodel @Inject constructor() : ViewModel(){

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState:StateFlow<HomeScreenUiState> = _uiState.asStateFlow()
}