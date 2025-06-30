package com.devrachit.groww.domain.usecases.watchlistDetails

import com.devrachit.groww.data.local.entity.WatchlistEntity
import com.devrachit.groww.domain.repository.local.WatchlistRepository
import com.devrachit.groww.utility.networkUtility.Resource
import com.devrachit.groww.utility.networkUtility.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class isStockInWatchList @Inject constructor(
    private val repository: WatchlistRepository
) {
    operator fun invoke(symbol: String) :Flow<Resource<List<WatchlistEntity>>> = flow {
        emit(Resource.Loading())
        val result = safeApiCall { repository.isStockInWatchlist(symbol) }
        emit(result)
    }
}