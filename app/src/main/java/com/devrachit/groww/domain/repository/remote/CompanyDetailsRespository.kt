package com.devrachit.groww.domain.repository.remote

import com.devrachit.groww.data.remote.dto.CompanyDetailsDto
import com.devrachit.groww.data.remote.dto.DailyGraphDataDto
import com.devrachit.groww.data.remote.dto.IntraDayGraphDto
import com.devrachit.groww.data.remote.dto.MonthlyGraphDataDto
import com.devrachit.groww.data.remote.dto.OhlcvDataDto
import com.devrachit.groww.data.remote.dto.WeeklyGraphDataDto
import com.devrachit.groww.domain.models.OhlcvData

interface CompanyDetailsRepository{
    suspend fun getCompanyDetails(ticker: String): CompanyDetailsDto

    suspend fun getIntraDayChartData(ticker: String): IntraDayGraphDto

    suspend fun getDailyChartData(ticker: String): DailyGraphDataDto

    suspend fun getWeeklyChartData(ticker: String): WeeklyGraphDataDto

    suspend fun getMonthlyChartData(ticker: String): MonthlyGraphDataDto

}