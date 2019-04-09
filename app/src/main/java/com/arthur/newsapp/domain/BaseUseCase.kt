package com.arthur.newsapp.domain

import com.arthur.newsapp.data.model.DataModel
import com.arthur.newsapp.data.model.ResponseModel
import com.arthur.newsapp.util.Result
import kotlinx.coroutines.Deferred
import timber.log.Timber
import java.io.IOException

open class BaseUseCase<T : DataModel> {
    suspend fun safeApiCall(call: suspend () -> Deferred<ResponseModel<T>>, errorMessage: String): List<T>? {

        val result  = safeApiResult(call,errorMessage)

        return when(result) {
            is Result.Success ->
                result.data
            is Result.Error -> {
                Timber.d( "$errorMessage & Exception - ${result.throwable}")
                null
            }
        }

    }

    private suspend fun  safeApiResult(call: suspend ()-> Deferred<ResponseModel<T>>, errorMessage: String) : Result<List<T>>{
        val response = call.invoke().await()
        if(response.status == "ok") return Result.Success(response.data)

        return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - ${response.status}"))
    }
}