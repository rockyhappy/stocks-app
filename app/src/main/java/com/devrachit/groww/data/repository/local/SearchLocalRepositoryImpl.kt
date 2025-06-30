package com.devrachit.groww.data.repository.local

import com.devrachit.groww.data.local.dao.SearchHistoryDao
import com.devrachit.groww.data.local.entity.SearchHistoryEntity
import com.devrachit.groww.domain.repository.local.SearchLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class SearchLocalRepositoryImpl @Inject constructor(
    private val searchLocalDataSource: SearchHistoryDao
): SearchLocalRepository{
    
    override suspend fun addToSearchHistory(history: SearchHistoryEntity) {
        try {
            // Check if query is not blank before adding
            if (history.query.isNotBlank()) {
                // Use the insertWithLimit method which handles duplicates and limits
                searchLocalDataSource.insertWithLimit(history)
            }
        } catch (e: Exception) {
            // Log error or handle as needed
            // In production, you might want to use a proper logging framework
            e.printStackTrace()
        }
    }

    override fun getSearchHistory(): Flow<List<SearchHistoryEntity>> {
        return searchLocalDataSource.getAllSearches()
            .catch { exception ->
                // Handle database errors gracefully
                exception.printStackTrace()
                emit(emptyList()) // Emit empty list on error
            }
    }
}
