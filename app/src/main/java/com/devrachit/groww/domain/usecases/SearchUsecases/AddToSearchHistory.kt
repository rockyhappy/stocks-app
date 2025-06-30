package com.devrachit.groww.domain.usecases.SearchUsecases

import com.devrachit.groww.data.local.entity.SearchHistoryEntity
import com.devrachit.groww.domain.repository.local.SearchLocalRepository
import javax.inject.Inject

class AddToSearchHistory @Inject constructor(
    private val repository: SearchLocalRepository
) {
    suspend operator fun invoke(query: SearchHistoryEntity) = repository.addToSearchHistory(query)
}