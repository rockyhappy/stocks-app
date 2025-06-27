package com.devrachit.groww.data.repository.remote

import com.devrachit.groww.data.remote.dto.CompanyDetailsDto
import com.devrachit.groww.data.remote.services.AlphaVantageApiService
import com.devrachit.groww.domain.models.CompanyDetails
import com.devrachit.groww.domain.repository.remote.CompanyDetailsRepository
import javax.inject.Inject

class CompanyDetailsRepositoryImpl @Inject constructor(
    private val apiService: AlphaVantageApiService
): CompanyDetailsRepository {
    override suspend fun getCompanyDetails(ticker: String): CompanyDetailsDto {
        return apiService.getCompanyDetails(ticker)
    }
}