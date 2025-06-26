package com.devrachit.groww.data.repository.remote

import com.devrachit.groww.data.remote.dto.TopGainersLosersActivesDto
import com.devrachit.groww.data.remote.services.AlphaVantageApiService
import com.devrachit.groww.domain.repository.remote.HomeRepository
import com.devrachit.groww.utility.networkUtility.Resource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: AlphaVantageApiService
): HomeRepository {
    override suspend fun getGainersLosersActives(): TopGainersLosersActivesDto {
        return apiService.getTopGainersLosers()
    }
}