package com.juanvilla.freshpic.data.utils

import com.juanvilla.freshpic.domain.exception.BaseException
import com.juanvilla.freshpic.domain.exception.InternalException
import com.juanvilla.freshpic.domain.exception.NotFoundException
import com.juanvilla.freshpic.domain.util.ResultType
import retrofit2.Response

suspend fun <T> safeNetworkCall(
    call: suspend () -> Response<T>
): ResultType<T> = try {
    val response = call()
    if (response.isSuccessful && response.body() != null) {
        ResultType.Success(response.body()!!)
    } else {
        handleErrorResponse(response)
    }
} catch (error: Throwable) {
    ResultType.Error(BaseException(""))
}

private fun <T> handleErrorResponse(response: Response<T>) = when (response.code()) {
    404 -> ResultType.Error(NotFoundException(message = "Not found"))
    500 -> ResultType.Error(InternalException(message = "Internal error"))
    else -> ResultType.Error(BaseException("Unknown Exception"))
}