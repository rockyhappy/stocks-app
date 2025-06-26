package com.devrachit.groww.domain.repository.remote

import com.devrachit.groww.data.remote.dto.TopGainersLosersActivesDto
import com.devrachit.groww.utility.networkUtility.Resource

interface HomeRepository {

    suspend fun getGainersLosersActives(): TopGainersLosersActivesDto

}