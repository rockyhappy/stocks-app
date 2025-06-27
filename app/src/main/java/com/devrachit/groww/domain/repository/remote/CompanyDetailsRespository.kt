package com.devrachit.groww.domain.repository.remote

import com.devrachit.groww.data.remote.dto.CompanyDetailsDto

interface CompanyDetailsRepository{
    suspend fun getCompanyDetails(ticker: String): CompanyDetailsDto
}