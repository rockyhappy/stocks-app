package com.devrachit.groww.domain.usecases.watchlistDetails

import com.devrachit.groww.data.local.entity.WatchlistEntity
import com.devrachit.groww.domain.repository.local.WatchlistRepository
import com.devrachit.groww.utility.networkUtility.Resource
import com.devrachit.groww.utility.networkUtility.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddWatchlist @Inject constructor(
    private val repository: WatchlistRepository
) {
    operator fun invoke(watchlistEntity: WatchlistEntity): Flow<Resource<Long>> = flow {
        emit(Resource.Loading())
        val result = safeApiCall { repository.addWatchList(watchlistEntity) }
        when {
            result.data != null -> emit(Resource.Success(result.data))
            else -> emit(Resource.Error(result.message ?: "Unknown error"))
        }
        emit(result)
    }
}