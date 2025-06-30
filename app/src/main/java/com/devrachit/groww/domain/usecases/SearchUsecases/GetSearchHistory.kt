package com.devrachit.groww.domain.usecases.SearchUsecases

import com.devrachit.groww.data.local.entity.SearchHistoryEntity
import com.devrachit.groww.domain.repository.local.SearchLocalRepository
import com.devrachit.groww.utility.networkUtility.Resource
import com.devrachit.groww.utility.networkUtility.safeApiCall
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetSearchHistory@Inject constructor(
    private val repository: SearchLocalRepository
) {
    operator fun invoke(): Flow<List<SearchHistoryEntity>> = repository.getSearchHistory()
}