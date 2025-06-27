package com.devrachit.groww.domain.usecases.GetGainerLoserActiveStocks

import retrofit2.HttpException
import com.devrachit.groww.data.remote.mappers.toDomainModel
import com.devrachit.groww.domain.models.TopGainersLosersActives
import com.devrachit.groww.domain.repository.remote.HomeRepository
import com.devrachit.groww.utility.networkUtility.Resource
import com.devrachit.groww.utility.networkUtility.safeApiCall
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetGainerLoserActiveStocks @Inject constructor(
    private val homeRepository: HomeRepository
){
    operator fun invoke(): Flow<Resource<TopGainersLosersActives>> = flow {
        emit(Resource.Loading())
        val result = safeApiCall { homeRepository.getGainersLosersActives().toDomainModel() }
        emit(result)

    }
}