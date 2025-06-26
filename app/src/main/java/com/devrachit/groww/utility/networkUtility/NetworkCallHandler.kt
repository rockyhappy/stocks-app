package com.devrachit.groww.utility.networkUtility

import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): Resource<T> {
    return runCatching {
        apiCall()
    }.fold(
        onSuccess = { Resource.Success(it) },
        onFailure = { throwable ->
            val message = when (throwable) {
                is HttpException -> throwable.localizedMessage
                    ?: "An unexpected error occurred\nResponse code: ${throwable.code()}"
                is IOException -> "Couldn't reach the server\nCheck your internet connection"
                else -> throwable.message ?: "An unknown error occurred"
            }
            throwable.printStackTrace()
            Resource.Error(message)
        }
    )
}
