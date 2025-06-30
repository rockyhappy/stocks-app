package com.devrachit.groww.data.repository.remote

import com.devrachit.groww.data.remote.dto.SearchResultDto
import com.devrachit.groww.data.remote.services.AlphaVantageApiService
import com.devrachit.groww.domain.repository.remote.SearchRemoteRepository
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class SearchRemoteRepositoryImpl@Inject constructor(
    private val searchRemoteDataSource: AlphaVantageApiService
): SearchRemoteRepository {
    
    override suspend fun search(query: String): SearchResultDto {
        return searchRemoteDataSource.search(query)
    }
}
