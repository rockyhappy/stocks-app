package com.devrachit.groww.data.remote.services

import com.devrachit.groww.data.remote.dto.CompanyDetailsDto
import com.devrachit.groww.data.remote.dto.DailyGraphDataDto
import com.devrachit.groww.data.remote.dto.IntraDayGraphDto
import com.devrachit.groww.data.remote.dto.MonthlyGraphDataDto
import com.devrachit.groww.data.remote.dto.SearchResultDto
import com.devrachit.groww.data.remote.dto.TopGainersLosersActivesDto
import com.devrachit.groww.data.remote.dto.WeeklyGraphDataDto
import com.devrachit.groww.utility.constants.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface AlphaVantageApiService {

    @GET("query?function=TOP_GAINERS_LOSERS")
    suspend fun getTopGainersLosers(
        @Query("apikey") apiKey: String = API_KEY,
    ): TopGainersLosersActivesDto

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyDetails(
        @Query("symbol") ticker: String,
        @Query("apikey") apiKey: String = API_KEY,
    ): CompanyDetailsDto

    @GET("query?function=TIME_SERIES_INTRADAY")
    suspend fun getIntaDayPrices(
        @Query("symbol") ticker: String,
        @Query("interval") interval: String = "5min",
        @Query("apikey") apiKey: String = API_KEY,
    ): IntraDayGraphDto

    @GET("query?function=TIME_SERIES_DAILY")
    suspend fun getDailyPrices(
        @Query("symbol") ticker: String,
        @Query("apikey") apiKey: String = API_KEY,
    ): DailyGraphDataDto

    @GET("query?function=TIME_SERIES_WEEKLY")
    suspend fun getWeaklyPrices(
        @Query("symbol") ticker: String,
        @Query("apikey") apiKey: String = API_KEY,
    ): WeeklyGraphDataDto

    @GET("query?function=TIME_SERIES_MONTHLY")
    suspend fun getMonthlyPrices(
        @Query("symbol") ticker: String,
        @Query("apikey") apiKey: String = API_KEY,
    ): MonthlyGraphDataDto

    @GET("query?function=SYMBOL_SEARCH")
    suspend fun search(
        @Query("keywords") query: String,
        @Query("apikey") apiKey: String = API_KEY,
    ): SearchResultDto

}