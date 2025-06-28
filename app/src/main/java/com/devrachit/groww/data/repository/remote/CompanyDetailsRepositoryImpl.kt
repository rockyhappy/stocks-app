package com.devrachit.groww.data.repository.remote

import com.devrachit.groww.data.remote.dto.CompanyDetailsDto
import com.devrachit.groww.data.remote.dto.DailyGraphDataDto
import com.devrachit.groww.data.remote.dto.IntraDayGraphDto
import com.devrachit.groww.data.remote.dto.MonthlyGraphDataDto
import com.devrachit.groww.data.remote.dto.OhlcvDataDto
import com.devrachit.groww.data.remote.dto.WeeklyGraphDataDto
import com.devrachit.groww.data.remote.services.AlphaVantageApiService
import com.devrachit.groww.domain.models.CompanyDetails
import com.devrachit.groww.domain.models.OhlcvData
import com.devrachit.groww.domain.repository.remote.CompanyDetailsRepository
import javax.inject.Inject

class CompanyDetailsRepositoryImpl @Inject constructor(
    private val apiService: AlphaVantageApiService
) : CompanyDetailsRepository {
    override suspend fun getCompanyDetails(ticker: String): CompanyDetailsDto {
        return apiService.getCompanyDetails(ticker)
    }

    override suspend fun getIntraDayChartData(ticker: String): IntraDayGraphDto {
        return apiService.getIntaDayPrices(ticker)
    }

    override suspend fun getDailyChartData(ticker: String): DailyGraphDataDto {
        return apiService.getDailyPrices(ticker)
    }

    override suspend fun getWeeklyChartData(ticker: String): WeeklyGraphDataDto {
        return apiService.getWeaklyPrices(ticker)
    }

    override suspend fun getMonthlyChartData(ticker: String): MonthlyGraphDataDto {
        return apiService.getMonthlyPrices(ticker)
    }
}