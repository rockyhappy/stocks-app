package com.devrachit.groww.domain.repository.remote

import com.devrachit.groww.data.remote.dto.CompanyDetailsDto
import com.devrachit.groww.data.remote.dto.IntraDayGraphDto
import com.devrachit.groww.data.remote.dto.OhlcvDataDto
import com.devrachit.groww.domain.models.OhlcvData

interface CompanyDetailsRepository{
    suspend fun getCompanyDetails(ticker: String): CompanyDetailsDto

    suspend fun getIntraDayChartData(ticker: String): IntraDayGraphDto
}