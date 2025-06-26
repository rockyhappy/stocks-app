package com.devrachit.groww.domain.usecases.UserModeSelectionUseCases

import com.devrachit.groww.data.local.datastore.DataStoreRepository
import com.devrachit.groww.ui.theme.ThemeMode
import javax.inject.Inject

class SetUserSelectedModeUseCase @Inject constructor(
    private val dataRepository: DataStoreRepository
) {
    
    suspend operator fun invoke(themeMode: ThemeMode) {
        val themeModeString = when (themeMode) {
            ThemeMode.LIGHT -> "LIGHT"
            ThemeMode.DARK -> "DARK"
            ThemeMode.SYSTEM -> "SYSTEM"
        }
        dataRepository.saveThemeMode(themeModeString)
    }
}
