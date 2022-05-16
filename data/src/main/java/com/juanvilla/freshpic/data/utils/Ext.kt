package com.juanvilla.freshpic.data.utils

import com.juanvilla.freshpic.domain.exception.BaseException
import com.juanvilla.freshpic.domain.util.ResultType
import retrofit2.Response

suspend fun <T> safeNetworkCall(
    call: suspend () -> Response<T>
): ResultType<T> = try {
    val response = call()
    if (response.isSuccessful && response.body() != null) {
        ResultType.Success(response.body()!!)
    } else {
        //TODO: Create handle error response
        ResultType.Error(BaseException(""))
    }
} catch (error: Throwable) {
    ResultType.Error(BaseException(""))
}