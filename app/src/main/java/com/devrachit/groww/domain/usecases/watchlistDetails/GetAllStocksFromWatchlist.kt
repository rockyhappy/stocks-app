package com.devrachit.groww.domain.usecases.watchlistDetails

import com.devrachit.groww.data.local.entity.StocksEntity
import com.devrachit.groww.data.local.mappers.toDomainModel
import com.devrachit.groww.domain.models.Stock
import com.devrachit.groww.domain.repository.local.WatchlistRepository
import com.devrachit.groww.utility.networkUtility.Resource
import com.devrachit.groww.utility.networkUtility.safeApiCall
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetAllStocksFromWatchlist @Inject constructor(
    private val repository: WatchlistRepository
)  {
    operator fun invoke(watchlistId: Int) : Flow<Resource<List<Stock>>> = flow {
        emit(Resource.Loading())
        val result = safeApiCall{ repository.getStocksFromWatchlist(watchlistId).map {it.toDomainModel()}}
        emit(result)
    }
}