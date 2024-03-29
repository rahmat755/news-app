package com.arthur.newsapp.domain

import com.arthur.newsapp.data.model.DataModel
import com.arthur.newsapp.data.model.ResponseModel
import com.arthur.newsapp.util.Result
import kotlinx.coroutines.Deferred
import timber.log.Timber
import java.io.IOException

open class BaseUseCase<T : DataModel> {
    suspend fun safeApiCall(call: suspend () -> Deferred<ResponseModel<T>>, errorMessage: String): List<T>? {
        return when(val result  = safeApiResult(call,errorMessage)) {
            is Result.Success ->
                result.data
            is Result.Error -> {
                Timber.d( "$errorMessage & Exception - ${result.throwable}")
                null
            }
        }
    }

    private suspend fun  safeApiResult(call: suspend ()-> Deferred<ResponseModel<T>>, errorMessage: String) : Result<List<T>>{
        return try {
            val response = call.invoke().await()
            if(response.status == "ok") Result.Success(response.data)
            else Result.Error(IOException("$errorMessage - ${response.status}"))
        } catch (e: Throwable){
            Result.Error(e)
        }
    }
}