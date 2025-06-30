package com.devrachit.groww.domain.repository.remote

import com.devrachit.groww.data.remote.dto.SearchResultDto

interface SearchRemoteRepository {
    suspend fun search(query: String): SearchResultDto

}