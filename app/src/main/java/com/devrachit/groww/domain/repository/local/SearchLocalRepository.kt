package com.devrachit.groww.domain.repository.local

import com.devrachit.groww.data.local.entity.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

interface SearchLocalRepository {
    fun getSearchHistory(): Flow<List<SearchHistoryEntity>>

    suspend fun addToSearchHistory(history: SearchHistoryEntity)
}