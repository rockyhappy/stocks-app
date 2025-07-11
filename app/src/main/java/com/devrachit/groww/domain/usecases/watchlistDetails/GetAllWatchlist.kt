package com.devrachit.groww.domain.usecases.watchlistDetails

import com.devrachit.groww.data.local.entity.WatchlistEntity
import com.devrachit.groww.domain.repository.local.WatchlistRepository
import com.devrachit.groww.utility.networkUtility.Resource
import com.devrachit.groww.utility.networkUtility.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllWatchlist @Inject constructor(
    private val repository: WatchlistRepository
) {
    operator fun invoke() :Flow<Resource<List<WatchlistEntity>>> = flow {
        emit(Resource.Loading())
        val result = safeApiCall { 
            // Update counts for all watchlists in one query
            repository.updateAllWatchlistCounts()
            // Get the updated watchlists
            repository.getWatchlist()
        }
        when {
            result.data!= null -> emit(Resource.Success(result.data))
            else -> emit(Resource.Error(result.message ?: "Unknown error"))
        }
        emit(result)
    }
}
