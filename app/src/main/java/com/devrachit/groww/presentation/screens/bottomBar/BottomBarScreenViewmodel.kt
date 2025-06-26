package com.devrachit.groww.presentation.screens.bottomBar

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrachit.groww.domain.usecases.UserModeSelectionUseCases.GetUserModeSelectionUseCase
import com.devrachit.groww.domain.usecases.UserModeSelectionUseCases.SetUserSelectedModeUseCase
import com.devrachit.groww.ui.theme.ThemeMode
import com.devrachit.groww.utility.networkUtility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BottomBarScreenViewmodel @Inject constructor(
    private val getUserModeSelectionUseCase: GetUserModeSelectionUseCase,
    private val setUserSelectedModeUseCase: SetUserSelectedModeUseCase
): ViewModel() {
    private val _uiStates = MutableStateFlow<BottomBarScreenUiState>(BottomBarScreenUiState())
    val uiState: StateFlow<BottomBarScreenUiState> = _uiStates.asStateFlow()

    init {
        observeThemeMode()
    }

    private fun observeThemeMode() {
        viewModelScope.launch(Dispatchers.IO) {
            getUserModeSelectionUseCase().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiStates.value = _uiStates.value.copy(
                            currentThemeMode = resource.data ?: ThemeMode.SYSTEM,
                            isLoading = false
                        )
                        // Apply theme immediately
                        withContext(Dispatchers.Main){
                            AppCompatDelegate.setDefaultNightMode(resource.data?.nightMode ?: ThemeMode.SYSTEM.nightMode)
                        }

                    }
                    is Resource.Error -> {
                        _uiStates.value = _uiStates.value.copy(
                            currentThemeMode = ThemeMode.SYSTEM,
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _uiStates.value = _uiStates.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun setThemeMode(themeMode: ThemeMode) {
        viewModelScope.launch(Dispatchers.IO) {
            setUserSelectedModeUseCase(themeMode)
            withContext(Dispatchers.Main) {
                AppCompatDelegate.setDefaultNightMode(themeMode.nightMode)
            }

        }
    }
}
