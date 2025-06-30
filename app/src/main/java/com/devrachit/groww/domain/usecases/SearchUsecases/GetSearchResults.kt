package com.devrachit.groww.domain.usecases.SearchUsecases

import com.devrachit.groww.data.local.mappers.toDomainModel
import com.devrachit.groww.domain.models.SearchResult
import com.devrachit.groww.domain.repository.remote.SearchRemoteRepository
import com.devrachit.groww.utility.networkUtility.Resource
import com.devrachit.groww.utility.networkUtility.safeApiCall
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetSearchResults@Inject constructor(
    private val repository: SearchRemoteRepository
) {
    operator fun invoke(query: String) : Flow<Resource<List<SearchResult>>> = flow{
        emit(Resource.Loading())
        val result = safeApiCall { repository.search("tesco").bestMatches.map { it.toDomainModel() } }
        emit(result)

    }
}