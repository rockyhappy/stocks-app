package com.devrachit.groww.presentation.screens.bottomBar

import com.devrachit.groww.ui.theme.ThemeMode

data class BottomBarScreenUiState(
    val title: String = "Bottom Bar",
    val currentThemeMode: ThemeMode = ThemeMode.SYSTEM,
    val isLoading: Boolean = false
)
