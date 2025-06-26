package com.devrachit.groww.domain.usecases.UserModeSelectionUseCases

import com.devrachit.groww.data.local.datastore.DataStoreRepository
import com.devrachit.groww.ui.theme.ThemeMode
import com.devrachit.groww.utility.networkUtility.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserModeSelectionUseCase @Inject constructor(
    private val dataRepository: DataStoreRepository
) {
    
    operator fun invoke(): Flow<Resource<ThemeMode>> {
        return dataRepository.themeMode
            .map { themeModeString ->
                try {
                    val themeMode = when (themeModeString) {
                        "LIGHT" -> ThemeMode.LIGHT
                        "DARK" -> ThemeMode.DARK
                        "SYSTEM" -> ThemeMode.SYSTEM
                        else -> ThemeMode.SYSTEM
                    }
                    Resource.Success(themeMode)
                } catch (e: Exception) {
                    Resource.Error("Failed to get theme mode: ${e.message}", ThemeMode.SYSTEM)
                }
            }
            .catch { e ->
                emit(Resource.Error("Error retrieving theme mode: ${e.message}", ThemeMode.SYSTEM))
            }
    }
}
