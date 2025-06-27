package com.devrachit.groww.domain.usecases.CompanyDetails

import com.devrachit.groww.domain.models.CompanyDetails
import com.devrachit.groww.domain.repository.remote.CompanyDetailsRepository
import com.devrachit.groww.utility.networkUtility.Resource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetCompanyDetails@Inject constructor(
    private val repository: CompanyDetailsRepository
) {
    operator fun invoke(symbol: String):Flow<Resource<CompanyDetails>> = flow{
        emit(Resource.Loading())
    }
}