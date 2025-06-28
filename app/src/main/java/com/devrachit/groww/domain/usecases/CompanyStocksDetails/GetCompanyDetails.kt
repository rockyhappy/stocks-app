package com.devrachit.groww.domain.usecases.CompanyStocksDetails

import com.devrachit.groww.data.remote.mappers.toDomainModel
import com.devrachit.groww.domain.models.CompanyDetails
import com.devrachit.groww.domain.repository.remote.CompanyDetailsRepository
import com.devrachit.groww.utility.networkUtility.Resource
import com.devrachit.groww.utility.networkUtility.safeApiCall
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetCompanyDetails@Inject constructor(
    private val repository: CompanyDetailsRepository
) {
    operator fun invoke(symbol: String):Flow<Resource<CompanyDetails>> = flow{
        emit(Resource.Loading())
        val result = safeApiCall {repository.getCompanyDetails(symbol).toDomainModel() }
        emit(result)
    }
}