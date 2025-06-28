package com.devrachit.groww.domain.usecases.CompanyStocksDetails

import com.devrachit.groww.data.remote.mappers.toDomainModel
import com.devrachit.groww.domain.models.OhlcvData
import com.devrachit.groww.domain.repository.remote.CompanyDetailsRepository
import com.devrachit.groww.utility.networkUtility.Resource
import com.devrachit.groww.utility.networkUtility.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMonthlyGraphData @Inject constructor(
    private val repository: CompanyDetailsRepository
){
    operator fun invoke(symbol: String):Flow<Resource<Map<String,OhlcvData>>>  = flow{
        emit(Resource.Loading())
        val result = safeApiCall { repository.getMonthlyChartData(symbol).toDomainModel() }
        emit(result)

    }
}